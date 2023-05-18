#!/bin/bash

error_msg() {
    echo -e "\e[31m$1\e[0m"
    exit 1
}

add_service_user() {
    useradd -r -s /usr/sbin/nologin lagatrix
    passwd -d lagatrix

    echo lagatrix ALL = /usr/sbin/useradd, /usr/sbin/usermod, /usr/sbin/deluser, \
    /bin/crontab, /bin/kill, /usr/bin/$1, /sbin/poweroff, /sbin/reboot, \
    /bin/passwd, !/usr/sbin/usermod root, !/bin/passwd root >> /etc/sudoers
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
    echo "Install components..."
    $1 $4 $3  > /dev/null 2>&1
    $1 $4 $2 $5  > /dev/null 2>&1
}

set_package_manager() {
    family=$(cat /etc/os-release | grep -w 'ID_LIKE\|ID' | awk -F = '{print $2}' | xargs)

    case "${family}" in
        *rhel*)
            package_manager=yum
            install_param=install
            update_param=update
            no_confirm=-y
            packages="lm_sensors bc sudo"
            java_package="java-openjdk"
        ;;
        *debian*)
            package_manager=apt
            install_param=install
            update_param=update
            no_confirm=-y
            packages="lm-sensors bc sudo"
            java_package="default-jdk"
        ;;
        *suse*)
            package_manager=zypper
            install_param=install
            update_param=update
            no_confirm=-n
            packages="lm_sensors bc cronie sudo"
            java_package="java-openjdk"
        ;;
        *arch*)
            package_manager=pacman
            install_param=-S
            update_param=-Sy
            no_confirm=--noconfirm
            packages="lm_sensors bc cronie sudo"
            java_package="jdk-openjdk"
        ;;
        *)
            error_msg "No supported family"
        ;;
    esac
} 

main() {
    set_package_manager

    if [ ! -f "/usr/bin/java" ]; then
        packages="$packages $java_package"
    fi

    if install_components $package_manager $install_param $update_param $no_confirm "$packages"; then

        sensors-detect --auto > /dev/null 2>&1

        if ls lagatrix-server.service lagatrix.conf lagatrix-server.sh lagatrix-server.jar > /dev/null 2>&1; then
            add_service_user $package_manager > /dev/null 2>&1

            set_jar_executable > /dev/null 2>&1
            set_bash_executable > /dev/null 2>&1

            set_service > /dev/null 2>&1

            set_logs > /dev/null 2>&1
            set_config > /dev/null 2>&1

            echo -e "\e[32mInstalation complete\e[0m"
        else
            error_msg "The files needed to install Lagatrix Server could not be found"
        fi
    else
        error_msg "The components were not installed correctly"
    fi
}

if (( $(id -u) == 0 )); then
    main
else
    error_msg "Need root permissions"
fi
