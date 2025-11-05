# --- Estágio 1: Build (Construir o .jar) ---
# Usa a imagem oficial do Maven com Java 21
FROM maven:3.9-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . .

# Roda o comando de build do Maven
RUN ./mvnw clean install -DskipTests

# --- Estágio 2: Run (Rodar a Aplicação) ---
# Usa uma imagem Java 21 "limpa" e pequena, apenas com o necessário para rodar (JRE)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app


# O nome do .jar vem do seu pom.xml (artifactId e version)
COPY --from=build /app/target/my-personal-blog-0.0.1-SNAPSHOT.jar app.jar

# Expoe a porta 8080 (que o Spring usa por padrao)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]