#!/bin/sh

sudo /usr/local/tomcat/bin/shutdown.sh
sudo rm -fr /usr/local/tomcat/webapps/authstudy*
sudo cp build/libs/authstudy.war /usr/local/tomcat/webapps/.
sudo /usr/local/tomcat/bin/startup.sh

