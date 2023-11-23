# Technical Test for Capitole Consulting

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?logo=spring&logoColor=white&style=for-the-badge)
![Postgres](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Code style](https://img.shields.io/badge/Code%20style-Google-lightgray?style=for-the-badge&logo=Google)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=bugs)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=tobon96_capitole-technical-test&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=tobon96_capitole-technical-test) 

A technical test for applying to Capitole Consulting.
With clean architecture, DDD, unit/integration tests, sonar cloud and swagger documentation.

## Run tests
To run tests go to the project root folder and enter in the terminal `./gradlew test`

## Run locally
To run the application, you have two options:
- Run with dev profile and have a PostgreSQL database running on port 5432.
- Deploy containers with `docker-compose up`

## Code analysis
Available code analysis made by Sonar can be found at https://sonarcloud.io/summary/overall?id=tobon96_capitole-technical-test.

## Documentation
Available endpoints can be found at http://localhost:8080/capitole-test/swagger-ui/index.html. You need
to be running the application locally in order to be able to access the provided URL.