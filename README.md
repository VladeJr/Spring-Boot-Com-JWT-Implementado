# 🛡️ JWT Spring Boot Authentication API

Este projeto é uma API REST desenvolvida com Spring Boot, utilizando autenticação baseada em JWT (JSON Web Token). É possível registrar, autenticar usuários e proteger rotas com base em roles (`USER`, `ADMIN`).

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3+
- Spring Security
- JWT (`io.jsonwebtoken`)
- Spring Data JPA
- Banco de dados H2 (em memória)
- Maven

---

## 📁 Estrutura do Projeto

```
├── controller         # Endpoints da API
├── dto               # Objetos de entrada/saída (DTOs)
├── entity            # Entidades JPA
├── enums             # Enumeração de roles
├── repository        # Repositórios JPA
├── security          # Filtro e utilitários de JWT
├── service           # Lógica de negócio
└── config            # Configurações de segurança (SecurityConfig)
```

---

## ⚙️ Como Rodar

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Executando a aplicação

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação iniciará em `http://localhost:8080`.

---

## 🔐 Autenticação

### 🔸 Registro

**POST** `/auth/register`

```json
{
  "name": "Nome",
  "email": "seuemail@email.com",
  "password": "123456"
}
```

📌 Role padrão: `USER`. Se deseja customizar, adicione no DTO um campo `role`.

---

### 🔸 Login

**POST** `/auth/login`

```json
{
  "email": "seuemail@email.com",
  "password": "123456"
}
```

🟢 Retorno:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 🛡️ Rotas Protegidas

Adicione o token JWT no cabeçalho das requisições protegidas:

```http
Authorization: Bearer <seu_token>
```

Rotas:

| Endpoint         | Role Necessária |
|------------------|------------------|
| `/user/**`       | USER             |
| `/admin/**`      | ADMIN            |
| `/auth/**`       | Livre acesso     |

---


## 👤 Autor

Desenvolvido por Vlademir Vinhoto Junior - Projeto de aprendizado JWT com Spring Boot.
