# 📦 API REST Genérica com MongoDB

API REST flexível e genérica construída com **Java + Spring Boot + MongoDB**, capaz de manipular qualquer tipo de entidade dinamicamente, sem a necessidade de criar modelos fixos para cada coleção.

---

## 📋 Descrição do Projeto

A API permite realizar operações completas de CRUD em qualquer coleção do MongoDB de forma dinâmica. O usuário pode criar, listar, buscar, atualizar e remover registros em qualquer coleção, além de utilizar paginação, filtros dinâmicos e projeções de campos.

---

## 🛠️ Tecnologias Utilizadas

- Java 21+
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Lombok
- Maven

---

## ⚙️ Como Instalar as Dependências

### Pré-requisitos

Para rodar este projeto, você só precisa ter instalado em sua máquina:

* [Docker](https://www.docker.com/)
* [Docker Compose](https://docs.docker.com/compose/)

### ▶️ Como Executar o Projeto com Docker Compose
O projeto está totalmente configurado para subir a API e o banco de dados MongoDB juntos de forma isolada e integrada.

**1. Clone o repositório:**
```bash
git clone <url-do-repositorio>
cd spring-mongodb-generic-CRUD
```
**2. Suba o ambiente completo:**

Execute o comando abaixo na raiz do projeto onde está o arquivo docker-compose.yml:

```
docker compose up -d
```

A API estará disponível automaticamente em: http://localhost:8080


## 🚀 Exemplos de Uso das Rotas

Todas as rotas seguem o padrão `/api/v1/{collection}`, onde `{collection}` é o nome de qualquer coleção do MongoDB.

---

### ➕ Criar um registro — `POST /{collection}`

```http
POST /api/v1/users
Content-Type: application/json

{
  "name": "João",
  "age": 22
}
```

```http
POST /api/v1/products
Content-Type: application/json

{
  "name": "Notebook",
  "price": 3500
}
```

---

### 📄 Listar todos os registros — `GET /{collection}`

```http
GET /api/v1/users
```

#### Com paginação:

```http
GET /api/v1/users?page=0&limit=10
```

#### Com filtros dinâmicos:

```http
GET /api/v1/users?query={"name":"João"}
GET /api/v1/users?query={"age":{"$gt":18}}
GET /api/v1/users?query={"name":{"$regex":"Jo"}}
```

#### Com projeções de campos:

```http
GET /api/v1/users?fields=name,age      # retorna só name e age
GET /api/v1/users?fields=-name         # retorna tudo exceto name
```

#### Combinando tudo:

```http
GET /api/v1/users?page=0&limit=5&query={"age":{"$gte":18}}&fields=name,age
```

---

### 🔍 Buscar por ID — `GET /{collection}/{id}`

```http
GET /api/v1/users/664f1a2b3c4d5e6f7a8b9c0d
```

---

### ✏️ Atualizar um registro — `PUT /{collection}/{id}`

```http
PUT /api/v1/users/664f1a2b3c4d5e6f7a8b9c0d
Content-Type: application/json

{
  "name": "Maria",
  "age": 34
}
```

---

### 🗑️ Remover um registro — `DELETE /{collection}/{id}`

```http
DELETE /api/v1/users/664f1a2b3c4d5e6f7a8b9c0d
```

---
