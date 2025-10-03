# Blog Pessoal - API

## 📜 Descrição

Esta é a API RESTful para o meu blog e portfólio pessoal. Desenvolvida do zero com a stack Java e Spring Boot, esta aplicação serve como um projeto prático para aplicar e demonstrar conceitos avançados de engenharia de software, incluindo arquitetura de APIs, segurança, design patterns (DTOs, Service Layer) e persistência de dados com um banco NoSQL.

## ✨ Funcionalidades


- CRUD completo para Usuários, Posts e Comentários.

- Sistema de autenticação e autorização baseado em Roles (ADMIN/USER).

- Tratamento de exceções centralizado.

- Validação de dados de entrada.

- Geração de slugs para URLs amigáveis de posts.

- Paginação nos endpoints de listagem.

## 🛠️ Tecnologias Utilizadas


- Java 21

- Spring Boot

- Spring Security

- Spring Data MongoDB

- MongoDB

- Maven

- Docker (para o ambiente de banco de dados)

- Springdoc (OpenAPI/Swagger)

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos

- Java 21 (JDK)
- Apache Maven 3.8+ 
- Docker

### Passos para Execução

1. **Clone o repositório:** 
```bash 
  git clone https://github.com/Coronel-Marc/Personal-Blog.git
  cd Personal-Blog
```
2. **Inicie o banco de dados MongoDB com Docker:** 
```bash
  docker run -d -p 27017:27017 --name meu-mongo -v mongo-data:/data/db mongo
```

3. **Configure as variáveis de ambiente locais:**<br>

Na pasta `src/main/resources`, crie um arquivo chamado `application-local.properties`. Adicione a seguinte propriedade para definir a senha do usuário `ADMIN` que será criado na primeira inicialização:
```properties
# src/main/resources/application-local.properties
seeder.admin.password=SuaSenhaSecretaAqui
```
*Este arquivo já está incluído no `.gitignore` para não ser enviado ao repositório.*
O perfil "local" já está ativado por padrão no arquivo `application.properties` principal.

4. **Execute a aplicação Spring Boot:** 
```bash
mvn spring-boot:run.
```
A aplicação estará disponível em `http://localhost:8080/api`

## 📖 Documentação da API

A documentação completa e interativa dos endpoints está disponível através do Swagger UI. Após iniciar a aplicação, acesse o seguinte link no seu navegador: http://localhost:8080/api/swagger-ui.html"

## ✒️ Autor
- Marcos Gabriel Ferreira Lima
- LinkedIn: [Marcos Gabriel](https://www.linkedin.com/in/g-marcos/)
- Github: [@Coronel-Marc](https://github.com/Coronel-Marc)