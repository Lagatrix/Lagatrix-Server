#!/bin/bash

add_service_user() {
    useradd -r -s /usr/sbin/nologin lagatrix
    passwd -d lagatrix

    echo lagatrix ALL = /usr/sbin/useradd, /usr/sbin/usermod, /usr/sbin/deluser \
    /usr/bin/crontab, /bin/kill, /usr/bin/$1, /sbin/poweroff, /sbin/reboot, !/usr/sbin/usermod root >> /etc/sudoers
}

set_jar_executable() {
    mv lagatrix-server.jar /usr/sbin/lagatrix-server.jar
    chown lagatrix:lagatrix /usr/sbin/lagatrix-server.jar
    chmod +x /usr/sbin/lagatrix-server.jar
}

set_bash_executable() {
    mv lagatrix-server.sh /usr/sbin/lagatrix-server.sh
    chown lagatrix:lagatrix /usr/sbin/lagatrix-server.sh
    chmod +x /usr/sbin/lagatrix-server.sh
}

set_service() {
    mv lagatrix-server.service /usr/lib/systemd/system/lagatrix-server.service
    systemctl enable lagatrix-server.service
    systemctl start lagatrix-server.service
}

set_logs() {
    mkdir /var/log/lagatrix/
    chown -R lagatrix /var/log/lagatrix/
}

set_config() {
    mv lagatrix.conf /etc/
    chown -R lagatrix /etc/lagatrix.conf
}

install_components() {
    $1 $4 $3 
    $1 $4 $2 $5
}

family=$(cat /etc/os-release | grep -w 'ID_LIKE\|ID' | awk -F = '{print $2}' | xargs)

case "${family}" in
    *rhel*)
        package_manager=yum
        install_param=install
        update_param=update
        no_confirm=-y
        packages="lm_sensors bc neofetch sudo"
        java_package="java-openjdk"
    ;;
    *debian*)
        package_manager=apt
        install_param=install
        update_param=update
        no_confirm=-y
        packages="lm-sensors bc neofetch sudo"
        java_package="default-jdk"
    ;;
    *suse*)
        package_manager=zypper
        install_param=install
        update_param=update
        no_confirm=-n
        packages="sensors bc cronie neofetch sudo"
        java_package="java-openjdk"
    ;;
    *arch*)
        package_manager=pacman
        install_param=-S
        update_param=-Sy
        no_confirm=-no-confirm
        packages="lm-sensors bc cronie neofetch sudo"
        java_package="jdk-openjdk"
    ;;
    *)
        echo -e "\e[31mNo supported family\e[0m"
        exit 1
    ;;
esac

if [ ! -f "/usr/bin/java" ]; then
    packages="$packages $java_package"
fi

install_components $package_manager $install_param $update_param $no_confirm "$packages"

add_service_user $package_manager

set_jar_executable
set_bash_executable

set_service

set_logs
set_config

sensors-detect --auto

echo -e "\e[32mInstalation complete\e[0m"
