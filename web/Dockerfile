FROM tomcat:8-jre8  
MAINTAINER lpradel

RUN echo "export JAVA_OPTS=\"-Dapp.env=staging\"" > /usr/local/bin/setenv.sh  
COPY ./PontoDeHoras.war /usr/local/tomcat/webapps/PontoDeHoras.war

CMD ["catalina.sh", "run"]  
