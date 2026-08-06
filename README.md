# Controle Financeiro API

API REST para controle financeiro pessoal, desenvolvida com Java 21, Spring Boot e PostgreSQL.

## Tecnologias

- Java 21
- Spring Boot
- Gradle
- Spring Web MVC
- Spring Data JPA
- Flyway
- PostgreSQL
- Bean Validation
- SpringDoc OpenAPI
- Lombok
- MapStruct

## Requisitos

- JDK 21
- PostgreSQL

## Configuração

A aplicação utiliza variáveis de ambiente para conexão com o banco de dados:

```text
DB_HOST=localhost
DB_PORT=5432
DB_NAME=controle_financeiro
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

## Executando o projeto

No Windows:

```powershell
.\gradlew.bat bootRun
```

Em Linux/macOS:

```bash
./gradlew bootRun
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

## Testes

```bash
./gradlew test
```

No Windows:

```powershell
.\gradlew.bat test
```

## Documentação da API

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui.html
```

## Migrações

As migrações do banco de dados devem ser criadas em:

```text
src/main/resources/db/migration
```

Seguindo o padrão do Flyway:

```text
V1__descricao_da_migracao.sql
```
