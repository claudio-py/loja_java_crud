<p align="right">
  <a href="#-português">🇧🇷</a> |
  <a href="#-english">🇺🇸</a>
</p>

# 📘 Relatório Técnico / Technical Report

---

## 🇧🇷 Português

### 📌 Descrição

Uma aplicação full stack multi-camada, totalmente conteinerizada, com separação clara de responsabilidades entre as camadas. Trata-se de um sistema modular, independente de plataforma ou sistema operacional, viabilizado pelo uso do Docker para a orquestração e o deploy dos serviços.

- **Camada de Dados:** Utiliza-se o SGBD Microsoft SQL Server, restaurado a partir de um arquivo `.bak`, como fonte principal de dados.

- **Camada de Persistência e Aplicação:** Desenvolvida com o framework **Spring Boot**, responsável pela lógica de negócio, controle de dependências e estrutura do backend. A persistência é feita por meio do **driver JDBC** com **ORM no padrão JPA**, usando a especificação **Jakarta EE** e a implementação **Hibernate**.

- **Servidor de Aplicação:** A aplicação Spring Boot roda em um **servidor Tomcat embutido**, atuando como servidor de aplicações leve. Ele é responsável pelo gerenciamento de requisições HTTP, sessões, servlets e integração backend. O uso do Tomcat embutido elimina a necessidade de servidores como JBoss ou GlassFish, simplificando o empacotamento em um único artefato `.jar`, ideal para ambientes conteinerizados.

- **Camada de Apresentação (Frontend):** Desenvolvida com **React**, utilizando ferramentas modernas como **Vite**, **Node.js** e **pnpm** para o empacotamento e construção da interface. O build gerado é servido via **NGINX**, que atua como servidor web e **proxy reverso**, servindo os arquivos estáticos e roteando requisições para o backend.

### 🧱 Principais Tecnologias Utilizadas

- Java (OpenJDK 21)
- Spring Boot (JPA, Hibernate, Jakarta EE)
- SQL Server (com recuperação de backup)
- React + Vite
- Node.js + pnpm
- NGINX
- Docker + Docker Compose
- Swagger (para documentação de APIs)

### 🚀 Requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### ⚙️ Como Executar

Clone o repositório, acesse o diretório raiz e execute:

```bash
docker compose build
docker compose up
```

A aplicação será inicializada com todos os serviços necessários. Acesse:

- http://localhost:3000 para o frontend

- http://localhost:8081/swagger-ui.html para visualizar os endpoints da API REST

## 🇺🇸 English

### 📌 Description

This is a fully containerized multi-layer full stack application, built with a clear separation of concerns across each layer. It is a modular, platform-independent system made possible through Docker for orchestration and service deployment.

- **Data Layer:** Utilizes **Microsoft SQL Server**, restored from a `.bak` file, as the primary relational database.

- **Persistence & Application Layers:** Implemented using the **Spring Boot** framework, which manages the business logic and handles dependency management. Data access is facilitated via **JDBC**, with **object-relational mapping (ORM)** using **JPA** (Jakarta EE specification) and **Hibernate** as the implementation.

- **Application Server:** The application runs on an **embedded Tomcat server**, acting as a lightweight application server responsible for handling HTTP requests, managing servlets and sessions, and integrating with the backend. This eliminates the need for traditional enterprise servers like JBoss or GlassFish, as Spring Boot embeds all necessary server functionality into a single executable `.jar` file. This makes the application lightweight and ideal for containerized environments.

- **Presentation Layer (Frontend):** Built with **React**, using **Vite**, **Node.js**, and **pnpm** for bundling and building the user interface. Static build files are served by **NGINX**, which also functions as a **web server** and **reverse proxy**, forwarding API requests to the backend.

### 🧱 Technologies Used

- Java (OpenJDK 21)
- Spring Boot (JPA, Hibernate, Jakarta EE)
- SQL Server (via backup restore)
- React + Vite
- Node.js + pnpm
- NGINX
- Docker + Docker Compose
- Swagger (for API documentation)

### 🚀 Requirements

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### ⚙️ How to Run

Clone the repository, navigate to the root directory, and run:

```bash
docker compose build
docker compose up
```
Once all services are up, access:

- http://localhost:3000 to use the React frontend

- http://localhost:8081/swagger-ui.html to view the REST API documentation