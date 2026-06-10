# SistemaWeb - PC Store API

API REST para la gestión de una tienda de componentes de PC. Desarrollada con Spring Boot 4, JPA, PostgreSQL y Lombok.

---

## Requisitos

- Java 26
- Maven 3.9+
- PostgreSQL 15+
- Docker (opcional)

---

## Base de datos

### Con Docker Compose

```bash
docker compose up -d
```

Esto levanta PostgreSQL en `localhost:5432` con la base de datos `pc_store`.

### Manual

Crear la base de datos en PostgreSQL:

```sql
CREATE DATABASE pc_store;
```

---

## Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

La API queda disponible en: `http://localhost:8080`

---

## Estructura del proyecto

```
src/main/java/com/PC/Store/SistemaWeb/
├── controller/
│   ├── api/               # Controladores REST (Postman)
│   │   ├── UsuarioApiController
│   │   ├── CategoriaApiController
│   │   ├── ProductoApiController
│   │   ├── PedidoApiController
│   │   └── DetallePedidoApiController
│   ├── controllerAdmin    # Vistas Thymeleaf (admin)
│   ├── controllerLogin    # Vista login
│   └── inicio             # Vistas usuario
├── dto/
│   ├── usuario/           # UsuarioRequestDTO, UsuarioResponseDTO
│   ├── categoria/         # CategoriaRequestDTO, CategoriaResponseDTO
│   ├── producto/          # ProductoRequestDTO, ProductoResponseDTO
│   ├── pedido/            # PedidoRequestDTO, PedidoResponseDTO
│   └── detalle/           # DetallePedidoRequestDTO, DetallePedidoResponseDTO
├── exception/
│   ├── ResourceNotFoundException
│   ├── BusinessException
│   ├── ErrorResponse
│   └── GlobalExceptionHandler
├── model/                 # Entidades JPA
├── repository/            # Interfaces JpaRepository
├── service/               # Interfaces de servicio
│   └── impl/              # Implementaciones
└── SistemaWebApplication
```

---

## Endpoints de la API

Base URL: `http://localhost:8080/api/v1`

---

### Usuarios `/api/v1/usuarios`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/usuarios` | Listar todos |
| GET | `/api/v1/usuarios/{id}` | Buscar por ID |
| POST | `/api/v1/usuarios` | Crear usuario |
| PUT | `/api/v1/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/v1/usuarios/{id}` | Eliminar por ID |
| DELETE | `/api/v1/usuarios/batch` | Eliminar por lista de IDs |

**POST / PUT body:**
```json
{
  "nombre": "Juan Pérez",
  "correo": "juan@example.com",
  "password": "segura1234",
  "rol": "ADMIN"
}
```

**Response:**
```json
{
  "idUsuario": 1,
  "nombre": "Juan Pérez",
  "correo": "juan@example.com",
  "rol": "ADMIN"
}
```

---

### Categorías `/api/v1/categorias`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/categorias` | Listar todas |
| GET | `/api/v1/categorias/{id}` | Buscar por ID |
| POST | `/api/v1/categorias` | Crear categoría |
| PUT | `/api/v1/categorias/{id}` | Actualizar categoría |
| DELETE | `/api/v1/categorias/{id}` | Eliminar por ID |
| DELETE | `/api/v1/categorias/batch` | Eliminar por lista de IDs |

**POST / PUT body:**
```json
{
  "nombre": "Procesadores",
  "descripcion": "CPUs Intel y AMD para escritorio y laptop"
}
```

---

### Productos `/api/v1/productos`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/productos` | Listar todos |
| GET | `/api/v1/productos/{id}` | Buscar por ID |
| POST | `/api/v1/productos` | Crear producto |
| PUT | `/api/v1/productos/{id}` | Actualizar producto |
| DELETE | `/api/v1/productos/{id}` | Eliminar por ID |
| DELETE | `/api/v1/productos/batch` | Eliminar por lista de IDs |

**POST / PUT body:**
```json
{
  "nombre": "Intel Core i9-14900K",
  "precio": 589.99,
  "stock": 15,
  "image": "https://ejemplo.com/imagen.jpg",
  "idCategoria": 1
}
```

**Response:**
```json
{
  "idProducto": 1,
  "nombre": "Intel Core i9-14900K",
  "precio": 589.99,
  "stock": 15,
  "image": "https://ejemplo.com/imagen.jpg",
  "idCategoria": 1,
  "nombreCategoria": "Procesadores"
}
```

---

### Pedidos `/api/v1/pedidos`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/pedidos` | Listar todos |
| GET | `/api/v1/pedidos/{id}` | Buscar por ID |
| GET | `/api/v1/pedidos/usuario/{idUsuario}` | Pedidos de un usuario |
| POST | `/api/v1/pedidos` | Crear pedido |
| DELETE | `/api/v1/pedidos/{id}` | Eliminar por ID |
| DELETE | `/api/v1/pedidos/batch` | Eliminar por lista de IDs |

**POST body:**
```json
{
  "idUsuario": 1,
  "detalles": [
    { "idProducto": 1, "cantidad": 2 },
    { "idProducto": 3, "cantidad": 1 }
  ]
}
```

**Response:**
```json
{
  "idPedido": 1,
  "fechaRegistro": "2026-06-09",
  "total": 1269.97,
  "idUsuario": 1,
  "nombreUsuario": "Juan Pérez",
  "detalles": [
    {
      "idDetalle": 1,
      "idProducto": 1,
      "nombreProducto": "Intel Core i9-14900K",
      "precioUnitario": 589.99,
      "cantidad": 2,
      "subtotal": 1179.98
    }
  ]
}
```

> Al crear un pedido, el stock de cada producto se reduce automáticamente.  
> Al eliminar un pedido, el stock se restaura.

---

### Detalles de Pedido `/api/v1/detalles`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/detalles` | Listar todos |
| GET | `/api/v1/detalles/{id}` | Buscar por ID |
| GET | `/api/v1/detalles/pedido/{idPedido}` | Detalles de un pedido |
| DELETE | `/api/v1/detalles/{id}` | Eliminar por ID |
| DELETE | `/api/v1/detalles/batch` | Eliminar por lista de IDs |

---

## Eliminar por lista de IDs (batch)

Disponible en todos los recursos. Enviar un array de IDs en el body:

```json
[1, 2, 3]
```

---

## Manejo de errores

Todas las respuestas de error siguen el mismo formato:

```json
{
  "status": 404,
  "mensaje": "Producto con id 99 no encontrado",
  "errores": null,
  "timestamp": "2026-06-09T10:30:00"
}
```

| Código | Causa |
|--------|-------|
| 400 | Validación fallida o regla de negocio violada |
| 404 | Recurso no encontrado |
| 500 | Error interno del servidor |

---

## Relaciones entre entidades

```
Usuario (1) ──────── (N) Pedido
Categoria (1) ──────── (N) Producto
Pedido (1) ──────── (N) DetallePedido
Producto (1) ──────── (N) DetallePedido
```

---

## Reglas de negocio

- No se puede eliminar una **Categoría** si tiene productos asociados.
- No se puede eliminar un **Producto** si está incluido en pedidos.
- Al crear un **Pedido**, se valida que haya stock suficiente para cada producto.
- El **total** del pedido y el **subtotal** de cada detalle se calculan automáticamente.
- La **contraseña** del usuario nunca se expone en los responses.
- No se permiten dos usuarios con el mismo **correo**.
