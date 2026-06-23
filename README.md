# 🧾 T-Shirt API

API REST desenvolvida em Java com Spring Boot para gerenciamento de produtos de uma loja de camisetas.  
O projeto simula um sistema real de catálogo de produtos com persistência em banco PostgreSQL.

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

---

## 📦 Funcionalidades

- Criar produto
- Listar todos os produtos
- Buscar produto por ID
- Atualizar produto
- Deletar produto
- População inicial do banco com `data.sql`

---

## 🗄️ Modelo do Produto

```json
{
  "id": 1,
  "name": "Camiseta Nike",
  "description": "Algodão premium",
  "price": 120.00,
  "stock": 10
}
