# DevOps Pipeline for Web Application

![Project Image](https://github.com/Achreef01/5ARCTIC4-G2-Kaddem/blob/main/Architecture.PNG)

## Overview
This project demonstrates a comprehensive DevOps pipeline for developing and deploying a web application. The pipeline integrates multiple tools to automate key stages of the development lifecycle, including:

- **Code management** using GitHub
- **Build automation** with Maven
- **Continuous Integration** using Jenkins
- **Containerization** via Docker
- **Monitoring** handled by Grafana and Prometheus

The architecture follows a **microservices** approach, with separate containers for the web backend, frontend, and a MySQL database.

## Tools & Technologies

- **GitHub**: Version control and code repository.
- **Maven**: Dependency management and build automation.
- **Jenkins**: Continuous integration for automated builds and tests.
- **Docker**: Containerization of application services.
- **MySQL**: Database for storing application data.
- **Grafana & Prometheus**: Monitoring and alerting.

## Architecture

The project consists of the following key components:
1. **Web Backend**: Deployed in a Docker container.
2. **Web Frontend**: Deployed in a Docker container.
3. **MySQL Database**: Deployed in a separate container to manage persistent data storage.
4. **Monitoring Stack**: Includes Grafana and Prometheus for real-time application monitoring and alerting.

## Pipeline Stages

1. **Code Checkout**: Source code is managed in GitHub.
2. **Build**: Maven is used to compile the application and resolve dependencies.
3. **Test**: Automated tests using JUnit or Mockito integrated within Jenkins.
4. **Containerization**: Docker is used to package the application into containers.
5. **Deployment**: Docker Compose is used to manage and deploy the containers.
6. **Monitoring**: Application health and performance are monitored via Prometheus, with dashboards visualized in Grafana.

## Contributing
Feel free to submit issues or pull requests to contribute to this project.
