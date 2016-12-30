MAINTAINER magixsource.cn#gmail.com

FROM java:openjdk-8-jre-alpine

ENV APP_SLEEP 0

# add directly the zip
ADD dist/*.zip /app.zip

RUN sh -c 'touch /app.zip'
VOLUME /tmp
EXPOSE 8080

# unzip files
RUN unzip -o -d /app /app.zip

# start act application
#CMD echo "The application will start in ${APP_SLEEP}s..." && \
#    sleep ${APP_SLEEP} && \
#    ./start
# java -Djava.security.egd=file:/dev/./urandom -jar /app.war

CMD ["/app/start"]
