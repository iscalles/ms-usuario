# ms-usuario — Microservicio de Gestión de Usuarios

Colegio Bernardo O'Higgins · Proyecto Libro de Clases Digital

Microservicio que gestiona toda la información de los **usuarios y sus perfiles** en el sistema. Expone APIs REST para CRUD de usuarios, docentes, estudiantes, apoderados, administrativos, roles y relaciones apoderado-estudiante. Es consumido por el BFF y por `ms-auth` durante el proceso de login.

---

## Responsabilidades

- Crear, editar, eliminar y consultar usuarios
- Gestionar perfiles especializados: Docente, Estudiante, Apoderado, Administrativo
- Asignar y revocar roles (DOCENTE, APODERADO, ADMINISTRATIVO, ESTUDIANTE)
- Gestionar relaciones Apoderado ↔ Estudiante
- Proporcionar endpoints internos con datos sensibles (RUT) para ms-auth

---

## Requisitos previos

| Herramienta | Versión |
|---|---|
| Java JDK | 21 |
| Maven | 3.8 o superior |
| Oracle Autonomous Database | Wallet configurado |

---

## Instalación y ejecución

```bash
# 1. Clonar el repositorio
git clone https://github.com/iscalles/ms-usuario.git
cd ms-usuario/usuarioService

# 2. Copiar el wallet de Oracle a la ruta configurada
# El wallet debe estar en:
# src/main/resources/wallet/Wallet_proyectoLibroAsistencia/

# 3. Compilar
mvn clean package -DskipTests

# 4. Ejecutar
mvn spring-boot:run
```

El servicio inicia en `http://localhost:8081`.

---

## Configuración (`application.properties`)

```properties
server.port=8081

# Base de datos Oracle (Autonomous Database)
spring.datasource.url=jdbc:oracle:thin:@proyectolibroasistencia_high?TNS_ADMIN=<ruta-wallet>
spring.datasource.username=ms_usuario
spring.datasource.password=<contraseña>
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect

# Seguridad deshabilitada (la protección la ejerce el BFF)
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
```

---

## Endpoints REST

### Usuarios (`/usuario`)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/usuario` | Listar todos (sin RUT — respuesta pública) |
| GET | `/usuario/interno` | Listar todos (con RUT y roles — uso interno entre microservicios) |
| GET | `/usuario/{id}` | Buscar por ID |
| GET | `/usuario/interno/{id}` | Buscar por ID con RUT y roles (consumido vía OpenFeign por ms-auth, ms-academico y ms-asistencia) |
| GET | `/usuario/interno/rut/{rut}` | Buscar por RUT (uso de ms-auth en login) |
| GET | `/usuario/interno/correo/{correo}` | Buscar por correo (uso interno) |
| GET | `/usuario/existencia/rut/{rut}` | Verificar si existe un usuario con ese RUT (`true`/`false`) |
| GET | `/usuario/existencia/correo/{correo}` | Verificar si existe un usuario con ese correo (`true`/`false`) |
| POST | `/usuario` | Crear usuario |
| PUT | `/usuario/{id}` | Actualizar usuario |
| DELETE | `/usuario/{id}` | Eliminar usuario |

### Roles (`/usuario-rol`)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/usuario-rol/usuario/{id}` | Roles de un usuario |
| POST | `/usuario-rol/usuario/{id}/rol/{rol}` | Asignar rol |
| DELETE | `/usuario-rol/usuario/{id}/rol/{rol}` | Quitar rol |

### Perfiles especializados
| Recurso | Base URL |
|---|---|
| Docentes | `/docente` |
| Estudiantes | `/estudiante` |
| Apoderados | `/apoderado` |
| Administrativos | `/administrativo` |
| Relaciones apoderado-alumno | `/apoderado-estudiante` |

Cada recurso soporta GET, POST, PUT y DELETE.

> **Regla de negocio en `/apoderado-estudiante`:** un mismo estudiante solo puede tener asociado un apoderado con parentesco `"Padre"` y uno con `"Madre"`; la API valida esto al crear la relación (`POST /apoderado-estudiante`, body: `idApoderado`, `idEstudiante`, `parentescoApoderadoEstudiante`).

---

## Verificación de funcionamiento en POSTMAN

```bash
# Listar todos los usuarios (requiere JWT desde el BFF)
http://localhost:8081/usuario

# Buscar usuario por RUT (uso interno de ms-auth)
http://localhost:8081/usuario/interno/9
```

---

## Modelo de datos (tablas en `ms_usuario`)

| Tabla | Descripción |
|---|---|
| `USUARIO` | Datos base: RUT, nombre, apellidos, correo, fecha nacimiento |
| `USUARIO_ROL` | Relación usuario-rol (clave compuesta: id_usuario + tipo_rol) |
| `DOCENTE` | Título profesional y especialidad del docente |
| `ESTUDIANTE` | Fecha de ingreso y estado del estudiante |
| `APODERADO` | Dirección y teléfono del apoderado |
| `ADMINISTRATIVO` | Cargo y departamento del administrativo |
| `APODERADO_ESTUDIANTE` | Relación con parentesco entre apoderado y estudiante |

---

## Patrones de diseño implementados

| Patrón | Implementación |
|---|---|
| **Repository** | Un repositorio JPA por entidad (7 repositorios) — abstrae el acceso a datos |
| **Service Layer** | Interfaces de servicio con implementaciones separadas (`ServiceImpl`) — separa lógica de negocio de los controladores |
| **DTO (Data Transfer Object)** | `UsuarioDTOResponse` (sin RUT, público) vs `UsuarioDTOInternal` (con RUT, interno) — expone solo los datos necesarios según el contexto |
| **Exception Handler** | `GlobalExceptionHandler` centraliza el manejo de errores y retorna mensajes descriptivos en JSON |

---

## Tecnologías

- Spring Boot 3.2.12 (bajado desde 4.0.5 para alinear el BOM de Spring Cloud 2023.0.0 con el resto de los microservicios y poder exponerse como cliente/servidor OpenFeign de forma consistente)
- Java 21
- Spring Data JPA + Hibernate
- Oracle Autonomous Database
- Jakarta Validation (Bean Validation)
- Jackson (serialización JSON con control de ciclos via `@JsonIgnore`)
- Maven (arquetipo `spring-boot-starter-parent`)
