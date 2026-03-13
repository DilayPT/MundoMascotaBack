# 🐾 Mundo Mascota — Backend API REST

Backend de la plataforma **Mundo Mascota**, una tienda y refugio de mascotas. Proyecto Integrador del Tercer Semestre de Desarrollo de Software.

## 🛠️ Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 17+ |
| Spring Boot | 3.2.3 |
| Spring Data JPA | Incluido en Spring Boot |
| Base de Datos | H2 (temporal) / MySQL (producción) |
| Maven | 3.x |

## 📁 Estructura del Proyecto

```
com.mundoMascota
├── MundoMascotaApplication.java   ← Punto de entrada
├── model/                         ← Entidades JPA (7 clases)
│   ├── Producto.java
│   ├── Mascota.java
│   ├── Cliente.java
│   ├── Adopcion.java
│   ├── Compra.java
│   ├── DetalleCompra.java
│   └── MetodoPago.java
├── repository/                    ← Interfaces JpaRepository (7)
├── service/                       ← Lógica de negocio (7 servicios)
└── controller/                    ← Endpoints REST (7 controladores)
```

## 🗃️ Entidades del Sistema

| Entidad | Tabla | Campos principales |
|---|---|---|
| `Producto` | `producto` | id, nombre, precio, stock |
| `Mascota` | `mascota` | id, nombre, especie, raza, edad, estadoAdopcion |
| `Cliente` | `cliente` | id, nombre, correo, telefono |
| `Adopcion` | `adopcion` | id, mascota (FK), cliente (FK), fechaAdopcion |
| `Compra` | `compra` | id, cliente (FK), fechaCompra, total, estado |
| `DetalleCompra` | `detalle_compra` | id, compra (FK), producto (FK), cantidad, precioUnitario, subtotal |
| `MetodoPago` | `metodo_pago` | id, compra (FK), tipoPago, monto, fechaPago |

## 🌐 Endpoints de la API

Todos los endpoints siguen el patrón REST estándar en `http://localhost:8080`:

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/api/productos` | Listar todos los productos |
| `GET` | `/api/productos/{id}` | Obtener producto por ID |
| `POST` | `/api/productos` | Crear producto |
| `PUT` | `/api/productos/{id}` | Actualizar producto |
| `DELETE` | `/api/productos/{id}` | Eliminar producto |

> Los mismos métodos están disponibles para: `/api/mascotas`, `/api/clientes`, `/api/adopciones`, `/api/compras`, `/api/detalles-compra`, `/api/metodos-pago`

## ▶️ Cómo Ejecutar el Proyecto

### Requisitos previos
- Java 17 o superior instalado
- Maven 3.x instalado (o usar IntelliJ IDEA que lo incluye)

### Pasos

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/TU_USUARIO/MundoMascotaBack.git
   cd MundoMascotaBack/mundoMascota
   ```

2. **Ejecutar la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

3. **Verificar que levantó** en `http://localhost:8080`

4. **Consola H2** (para ver la base de datos): `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:mundoMascota`
   - Usuario: `sa` | Contraseña: *(vacía)*

### Abrir en IntelliJ IDEA

1. `File → Open` → seleccionar la carpeta `mundoMascota`
2. IntelliJ detecta Maven automáticamente y descarga las dependencias
3. Ejecutar `MundoMascotaApplication.java` con el botón ▶️

## 🔄 Cambiar a MySQL (producción)

Cuando el equipo de base de datos entregue las credenciales, editar `application.properties`:

```properties
# Comentar el bloque H2 y descomentar:
spring.datasource.url=jdbc:mysql://localhost:3306/mundo_mascota?useSSL=false&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

> ⚠️ No es necesario cambiar ningún archivo Java — solo `application.properties`.

## 👥 Equipo

| Nombre | Rol |
|---|---|
| Dylan Perez Tirado | Backend |
| Esteban Osorio | Backend |
| Kevin Perea | Frontend |
| James Seguro | Frontend |
| Jesus Barrios | Frontend |
| Luna Ocampo | SQL |
