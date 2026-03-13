
## ⚙️ Cómo se inicializó el proyecto

El proyecto se generó con la estructura estándar de Maven para Spring Boot:

```
com.mundoMascota
└── MundoMascotaApplication.java   ← @SpringBootApplication
```

La clase principal activa todo el framework con una sola anotación:

```java
@SpringBootApplication
public class MundoMascotaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MundoMascotaApplication.class, args);
    }
}
```

> `@SpringBootApplication` es equivalente a tres anotaciones juntas:
> - `@Configuration` → configura el contexto de Spring
> - `@EnableAutoConfiguration` → configura automáticamente según las dependencias
> - `@ComponentScan` → detecta todos los `@Controller`, `@Service`, `@Repository`

---

## 📦 Dependencias usadas (`pom.xml`)

| Dependencia | Para qué sirve |
|---|---|
| `spring-boot-starter-web` | Crear endpoints REST con `@RestController` |
| `spring-boot-starter-data-jpa` | Conectar con bases de datos usando JPA/Hibernate |
| `mysql-connector-j` | Driver JDBC para conectarse a MySQL |
| `h2` | Base de datos en memoria (para pruebas sin MySQL) |
| `spring-boot-devtools` | Recarga automática al guardar cambios en IntelliJ |
| `spring-boot-starter-test` | Herramientas para escribir pruebas unitarias |

---

## 🏷️ Anotaciones de Spring Boot utilizadas

### En los modelos (`@Entity`)
```java
@Entity                          // Le dice a JPA que esta clase es una tabla
@Table(name = "Mascota")         // Nombre exacto de la tabla en MySQL
public class Mascota {
    @Id                          // Campo que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT
    @Column(name = "ID_Mascota") // Nombre exacto de la columna en MySQL
    private Long idMascota;

    @ManyToOne                   // Relación muchos a uno
    @JoinColumn(name = "ID_Cliente") // Columna FK en la tabla
    private Cliente cliente;
}
```

### En los repositorios (`@Repository`)
```java
@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {}
//                                                        ↑       ↑
//                                                     Entidad   Tipo del ID
```
No se escribe ningún método — Spring genera automáticamente:
- `findAll()` → SELECT * FROM Mascota
- `findById(id)` → SELECT * WHERE ID = ?
- `save(entidad)` → INSERT o UPDATE
- `deleteById(id)` → DELETE WHERE ID = ?

### En los servicios (`@Service`)
```java
@Service
public class MascotaService {
    @Autowired                          // Inyección de dependencias automática
    private MascotaRepository repo;

    public List<Mascota> listarTodos() {
        return repo.findAll();          // Delega al repositorio
    }
}
```

### En los controladores (`@RestController`)
```java
@RestController                         // Combina @Controller + @ResponseBody (devuelve JSON)
@RequestMapping("/api/mascotas")        // Prefijo de todos los endpoints de esta clase
public class MascotaController {
    @Autowired
    private MascotaService service;

    @GetMapping                         // GET /api/mascotas
    public List<Mascota> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")                // GET /api/mascotas/1
    public ResponseEntity<Mascota> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping                        // POST /api/mascotas  (body: JSON)
    public Mascota crear(@RequestBody Mascota m) {
        return service.guardar(m);
    }

    @PutMapping("/{id}")               // PUT /api/mascotas/1  (body: JSON)
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota m) { ... }

    @DeleteMapping("/{id}")            // DELETE /api/mascotas/1
    public ResponseEntity<Void> eliminar(@PathVariable Long id) { ... }
}
```

---

## 🔌 Cómo se conecta con MySQL

Spring Boot se conecta a MySQL mediante el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Mundo_Mascota
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

**Hibernate** (implementación de JPA incluida en Spring) traduce automáticamente las clases Java con `@Entity` en tablas SQL.

---

## 🧪 Cómo probar la API

### Con Postman o un navegador:

| Operación | Método | URL | Body (JSON) |
|---|---|---|---|
| Ver todas las mascotas | `GET` | `http://localhost:8080/api/mascotas` | — |
| Ver mascota por ID | `GET` | `http://localhost:8080/api/mascotas/1` | — |
| Crear mascota | `POST` | `http://localhost:8080/api/mascotas` | `{"nombre":"Max","tipo":"Perro",...}` |
| Actualizar mascota | `PUT` | `http://localhost:8080/api/mascotas/1` | `{"nombre":"Max","tipo":"Gato",...}` |
| Eliminar mascota | `DELETE` | `http://localhost:8080/api/mascotas/1` | — |

> El mismo patrón aplica para todos los endpoints: `/api/clientes`, `/api/empleados`, `/api/ventas`, `/api/productos`, `/api/compras-producto`, `/api/citas-adopcion`, `/api/citas-mascota`

### Ejemplo de JSON para crear una Mascota:
```json
{
  "nombre": "Bobby",
  "tipo": "Perro",
  "raza": "Beagle",
  "edad": 4,
  "estadoSalud": "Vacunado",
  "modalidad": "Venta",
  "estadoDisponibilidad": "Disponible",
  "fechaIngreso": "2025-04-15",
  "procedencia": "Criadero B"
}
```

### Ejemplo de JSON para crear una Venta (con FK):
```json
{
  "fecha": "2025-05-18",
  "precio": 350.00,
  "mascota": { "idMascota": 1 },
  "cliente": { "idCliente": 1 },
  "empleado": { "idEmpleado": 1 }
}
```

---

## ▶️ Comandos para correr el proyecto

```bash
# Compilar
mvn clean compile

# Ejecutar servidor (queda escuchando en puerto 8080)
mvn spring-boot:run

# Compilar y empaquetar en .jar
mvn clean package
```

También desde **IntelliJ IDEA**: botón ▶️ en `MundoMascotaApplication.java`
