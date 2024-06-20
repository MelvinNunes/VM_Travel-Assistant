# API DETAILS

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

This project is an API built using **Java 17, Java Spring, Flyway Migrations, PostgresSQL as the database, and Spring Security and JWT for authentication control.**

## Table of Contents
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Internationalization](#Internationalization)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)
- [Testing](#testing)

## Installation
1.  Install dependencies with Maven
2.  Optional - install docker engine (https://docs.docker.com/engine/install)
3.  Optional (if you don't want to run in docker containers) - Install [PostgresSQL](https://www.postgresql.org/)

## Configuration
First you need to configure the .env variables, you can check the .env.example file and follow up.
After the initial setup and loading dependencies, you can proceed to usage.

## Usage

1. Start the project.
2. The API will be accessible at http://{your_host}:{your_port}, ex: http://localhost:8080
3. You can start the application using Docker (Compose module) if you have, just run `docker compose  --profile default up -d` (if you provided the .env file) in the root path.

## Internationalization

This API supports Internationalization (i18n) for both Portuguese and English languages. The language selection is based on the Accept-Language header provided in the HTTP request. The supported values for this header are:

1. "pt" for Portuguese
2. "en" for English

3. By including the appropriate Accept-Language header with one of these values in your requests, the API will respond with content in the corresponding language.

## API Endpoints
The API provides the following endpoints:

```markdown
DOCUMENTATION: go to  yourhost:port/docs.html path in your browser
```

## Authentication
The API uses Spring Security for authentication control with JWT Tokens.
To access protected endpoints, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes `PostgreSQL` as the database. The necessary database migrations are managed using Flyway.

## Testing
To test the project you need to set up a test database. I have defined a test database in docker, just run `docker compose --profile test up -d`
If you have maven you can run all test with `mvn test`


