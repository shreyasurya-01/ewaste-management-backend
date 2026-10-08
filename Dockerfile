FROM eclipse-temurin:26-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/ewaste-management-0.0.1-SNAPSHOT.jar"]