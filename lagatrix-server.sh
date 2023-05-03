#!/bin/bash

function error_msg () {
    echo -e "\e[31m$1\e[0m"
    exit 1
}

function logo () {
    echo "
##          ###     ######      ###    ######## ########  #### ##     ## 
##         ## ##   ##    ##    ## ##      ##    ##     ##  ##   ##   ##  
##        ##   ##  ##         ##   ##     ##    ##     ##  ##    ## ##   
##       ##     ## ##   #### ##     ##    ##    ########   ##     ###    
##       ######### ##    ##  #########    ##    ##   ##    ##    ## ##   
##       ##     ## ##    ##  ##     ##    ##    ##    ##   ##   ##   ##  
######## ##     ##  ######   ##     ##    ##    ##     ## #### ##     ##
"
}

if [ -f "/usr/bin/java" ]; then
    java_version=$(/usr/bin/java -version 2>&1 | head -n 1 | cut -d" " -f3 | cut -d"." -f1-2 | cut -d"\"" -f2)
    if (( $(echo "$java_version > 1.7" | bc -l) )); then
        if [ -f "/usr/sbin/lagatrix-server.jar" ]; then
            logo
            /usr/bin/java -jar /usr/sbin/lagatrix-server.jar
        else 
            error_msg "The executable JAR of lagatrix server not exist or not found in /usr/sbin/"
        fi
    else
        error_msg "The JRE version $java_version is older, the minimum version is 1.8"
    fi
else
    error_msg "Java is not instaled"
fi
