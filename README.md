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

- Java 21 ou superior instalado
- Maven instalado
- MongoDB rodando localmente na porta `27017`

### Instalando

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre na pasta do projeto
cd trabalho-ii-unidade-nosql
```

#### Linux/Mac:
```bash
./mvnw install
```

#### Windows:
```bash
.\mvnw.cmd install
```

---

## ▶️ Como Executar o Projeto

#### Linux/Mac:
```bash
./mvnw spring-boot:run
```

#### Windows:
```bash
.\mvnw.cmd spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 🔧 Configuração do Banco de Dados

No arquivo `src/main/resources/application.yaml`:

```yaml
spring:
  application:
    name: trabalho-ii-unidade-nosql
  mongodb:
    uri: mongodb://localhost:27017/nome-do-seu-banco
```
## 🐳 MongoDB com Docker

Caso não queira instalar o MongoDB localmente, você pode subi-lo via Docker.

### Pré-requisitos
- [Docker](https://docs.docker.com/get-docker/) instalado

### Subindo o MongoDB

```bash
docker run -d -p 27017:27017 --name mongodb mongo:7
```

O MongoDB estará disponível em `mongodb://localhost:27017`.

Para parar:
```bash
docker stop mongodb
```

---

---

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
