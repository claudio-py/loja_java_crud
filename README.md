<p align="right">
  <a href="#-português">🇧🇷</a> |
  <a href="#-english">🇺🇸</a>
</p>

# 📘 Relatório Técnico / Technical Report

---

## 🇧🇷 Português

### 📌 Descrição

Este repositório contém a implementação de uma atividade prática que aborda conceitos de persistência de dados em aplicações Java, utilizando JDBC, o padrão DAO e banco de dados SQL Server. O projeto está totalmente dockerizado para facilitar o setup e garantir portabilidade entre ambientes.

### 🧱 Tecnologias Utilizadas:

- Java openjdk-21
- JDBC
- SQL Server
- DAO Pattern
- Docker
- Docker Compose

### 🚀 Requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### ⚙️ Como Executar

Clone o repositório e acesse o diretório raiz do projeto e então execute:

```bash
docker compose build
docker compose run
```

A aplicação será inicializada com todos os serviços necessários, incluindo o servidor sql e a aplicação Java disponivel para interação diretamente no terminal. para realização de testes basta executar os metodos disponiveis no pacote com.loja na classe CadastroBDTest.java em sua IDE preferida. 

## 🇺🇸 English

### 📌 Description

This repository contains the implementation of a practical activity that covers data persistence concepts in Java applications, using JDBC, the DAO pattern, and SQL Server as the database. The project is fully dockerized to simplify setup and ensure portability across environments.
### 🧱 Technologies Used:
- Java openjdk-21
- JDBC
- SQL Server
- DAO Pattern
- Docker
- Docker Compose

### 🚀 Requirements

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

⚙️ How to Run
Clone the repository and navigate to the root project directory then run:
```bash
docker compose build
docker compose run
```
 
the app is going to start with all the necessary services, including the sql server and the java app itself available to interaction directly from the terminal, to run the tests you just need to execute the methods in the class CadastroBDTest from package com.loja, using your preferred IDE