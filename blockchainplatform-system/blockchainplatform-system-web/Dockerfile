FROM java:8

MAINTAINER springrain

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms512m -Xmx2048m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /springrain-system-web

WORKDIR /springrain-system-web

EXPOSE 8080

ADD ./target/springrain-system-web-6.0.0-SNAPSHOT.jar ./

CMD sleep 60;java $JAVA_OPTS -jar springrain-system-web-6.0.0-SNAPSHOT.jar
