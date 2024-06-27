
# Creditas Challenge

Olá! Este é o meu projeto para o desafio back-end proposto pela Creditas. O objetivo é desenvolver uma API ara disponibilizar a uma pessoa as modalidades de empréstimo as quais ela tem acesso, de acordo com algumas variáveis como idade, localização e renda.

## Requisitos
 - [Desafio](https://github.com/Creditas/challenge/blob/master/backend/code-challenges/java/README.pt-BR.md)

## Funcionalidades
- Buscar opcoes de emprestimos

## Tecnologias Utilizadas
- Java
- SpringBoot Web
- JUnit
- Design Patterns: Factory, Chain of Responsibility



## Como rodar o projeto

Clone o projeto

```bash
git clone https://github.com/andreparelho/loan-challenge
```

Navegue até o diretorio do projeto
```bash
cd br.com.bank.app
```

Execute a aplicação
```bash
./mvnw spring-boot:run
```b

A aplicação estará disponível em http://localhost:8080

## Endpoints
- `POST /v1/loan:` receber opcoes; 
