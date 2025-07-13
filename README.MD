# 🐳 Products Management

## 🚀 Levantar base de datos

Para iniciar la base de datos, ejecuta el siguiente comando:

```bash
docker compose up -d
```

## 📊 Script de creación de tablas

Ejecuta el siguiente script SQL para crear la tabla de productos:

```sql
CREATE TABLE products (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    price INT,
    description TEXT
);
```
