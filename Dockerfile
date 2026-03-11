FROM eclipse-temurin:17
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package
CMD ["java","-jar","target/*.jar"]