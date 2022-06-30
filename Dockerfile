FROM adoptopenjdk/openjdk11:alpine as build
WORKDIR /app

COPY pom.xml .
COPY src src

COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

RUN ./mvnw clean install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp

ARG DEPENDENCY=/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.berk2s.dentist.DentistBackendApplication"]
