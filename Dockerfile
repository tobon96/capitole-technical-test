FROM azul/zulu-openjdk:17 as BUILD
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradlew $APP_HOME/.
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build

FROM azul/zulu-openjdk:17
ARG DB_HOSTNAME
ARG DB_USER
ARG DB_PASSWORD
ENV DB_HOSTNAME $DB_HOSTNAME
ENV DB_USER $DB_USER
ENV DB_PASSWORD $DB_PASSWORD
ENV ARTIFACT_NAME=technical-test-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD "java" "-jar" $ARTIFACT_NAME