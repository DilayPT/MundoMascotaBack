# 🏗️ Arquitectura del Proyecto — Mundo Mascota

## ¿Cómo funciona el proyecto?

El sistema sigue la arquitectura **MVC por capas** de Spring Boot. El cliente (Frontend o Postman) envía peticiones HTTP, que atraviesan las capas en este orden:

```
[ Frontend / Postman ]
         ↓  HTTP Request (GET, POST, PUT, DELETE)
  [ Controller ]   ← Recibe y responde las peticiones
         ↓
   [ Service ]     ← Contiene la lógica de negocio
         ↓
  [ Repository ]   ← Habla con la base de datos
         ↓
  [ Base de Datos MySQL — Mundo_Mascota ]
```

---

## 📦 Capas del Proyecto

### 1. 🌐 Capa Controller (`/controller`)
**¿Qué hace?** Recibe las peticiones HTTP del cliente y devuelve las respuestas JSON.

**Anotación:** `@RestController`

| Archivo | Endpoint base |
|---|---|
| `MascotaController.java` | `/api/mascotas` |
| `ClienteController.java` | `/api/clientes` |
| `EmpleadoController.java` | `/api/empleados` |
| `VentaController.java` | `/api/ventas` |
| `ProductoController.java` | `/api/productos` |
| `CompraProductoController.java` | `/api/compras-producto` |
| `CitaAdopcionController.java` | `/api/citas-adopcion` |
| `CitaMascotaController.java` | `/api/citas-mascota` |

Cada controlador expone 5 operaciones:
- `GET /api/entidad` → listar todos
- `GET /api/entidad/{id}` → buscar por ID
- `POST /api/entidad` → crear nuevo
- `PUT /api/entidad/{id}` → actualizar
- `DELETE /api/entidad/{id}` → eliminar

---

### 2. ⚙️ Capa Service (`/service`)
**¿Qué hace?** Contiene la lógica de negocio. El Controller llama al Service, y el Service llama al Repository.

**Anotación:** `@Service`

| Archivo | Responsabilidad |
|---|---|
| `MascotaService.java` | CRUD de mascotas |
| `ClienteService.java` | CRUD de clientes |
| `EmpleadoService.java` | CRUD de empleados |
| `VentaService.java` | CRUD de ventas |
| `ProductoService.java` | CRUD de productos |
| `CompraProductoService.java` | CRUD de compras de productos |
| `CitaAdopcionService.java` | CRUD de citas de adopción |
| `CitaMascotaService.java` | CRUD de tabla puente Cita-Mascota |

---

### 3. 🗄️ Capa Repository (`/repository`)
**¿Qué hace?** Es el puente entre el código Java y la base de datos MySQL. Cada repositorio se encarga de **una sola tabla** y provee operaciones de lectura y escritura sin necesidad de escribir SQL manualmente.

**Anotación:** `@Repository` + `extends JpaRepository<ENTIDAD, TIPO_ID>`

**¿Cómo funciona?** Al extender `JpaRepository`, Spring Data JPA genera automáticamente todas las consultas SQL en tiempo de ejecución:

| Método que hereda | Consulta SQL que genera |
|---|---|
| `findAll()` | `SELECT * FROM tabla` |
| `findById(id)` | `SELECT * FROM tabla WHERE id = ?` |
| `save(entidad)` | `INSERT INTO ...` o `UPDATE ...` |
| `deleteById(id)` | `DELETE FROM tabla WHERE id = ?` |
| `count()` | `SELECT COUNT(*) FROM tabla` |
| `existsById(id)` | `SELECT COUNT(*) WHERE id = ?` |

> No se escribe ningún método adicional en las interfaces — Spring los genera solos. Esto es parte de **Spring Data JPA**.

**Archivos en el proyecto:**

| Archivo | Entidad | Tipo de ID |
|---|---|---|
| `MascotaRepository.java` | Mascota | Long |
| `ClienteRepository.java` | Cliente | Long |
| `EmpleadoRepository.java` | Empleado | Long |
| `VentaRepository.java` | Venta | Long |
| `ProductoRepository.java` | Producto | Long |
| `CompraProductoRepository.java` | CompraProducto | Long |
| `CitaAdopcionRepository.java` | CitaAdopcion | Long |
| `CitaMascotaRepository.java` | CitaMascota | CitaMascotaId *(clave compuesta: ID_Cita + ID_Mascota)* |

**Ejemplo del código:**
```java
@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    // Sin código adicional — Spring genera todo automáticamente
}
```

---

### 4. 📋 Capa Model (`/model`)
**¿Qué hace?** Define las clases Java que representan las tablas de la base de datos.

**Anotación:** `@Entity` + `@Table(name="...")`

| Clase Java | Tabla MySQL | Relaciones |
|---|---|---|
| `Mascota.java` | `Mascota` | — |
| `Cliente.java` | `Cliente` | — |
| `Empleado.java` | `Empleado` | — |
| `Producto.java` | `Producto` | — |
| `Venta.java` | `Venta` | FK → Mascota, Cliente, Empleado |
| `CompraProducto.java` | `CompraProducto` | FK → Cliente, Producto |
| `CitaAdopcion.java` | `CitaAdopcion` | FK → Cliente, Empleado |
| `CitaMascota.java` | `Cita_Mascota` | FK → CitaAdopcion, Mascota (clave compuesta) |

---

## 🗃️ Base de Datos

**Motor:** MySQL  
**Base de datos:** `Mundo_Mascota`  
**Configuración:** `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Mundo_Mascota
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update  ← Hibernate crea/actualiza tablas solo
```

> `ddl-auto=update` significa que cuando corres el proyecto, Hibernate revisa las entidades Java y crea o modifica las tablas en MySQL automáticamente si no existen.

---

## 🔄 Diagrama Relacional de Tablas

```
Mascota ←──────── Venta ──────────→ Cliente
                    │
                    └──────────────→ Empleado

Producto ←──── CompraProducto ─────→ Cliente

Cliente ←──── CitaAdopcion ─────────→ Empleado
                    │
              Cita_Mascota ──────────→ Mascota
```

---

## 📁 Estructura de Carpetas

```
mundoMascota/
├── pom.xml                          ← Dependencias Maven
└── src/main/
    ├── resources/
    │   └── application.properties   ← Configuración MySQL
    └── java/com/mundoMascota/
        ├── MundoMascotaApplication.java  ← Punto de entrada (@SpringBootApplication)
        ├── model/        ← Entidades JPA (tablas)
        ├── repository/   ← Acceso a datos (JpaRepository)
        ├── service/      ← Lógica de negocio
        └── controller/   ← Endpoints REST (API)
```

---

## 🚀 ¿Cómo correr el proyecto?

1. Asegurarse de que MySQL esté corriendo
2. Haber ejecutado el script SQL (`Mundo_Mascota`)
3. Configurar la contraseña en `application.properties`
4. Ejecutar `mvn spring-boot:run` o desde IntelliJ con ▶️
5. La API estará disponible en `http://localhost:8080`
