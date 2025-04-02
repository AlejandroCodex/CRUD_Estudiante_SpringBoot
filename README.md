# Proyecto: CRUD Estudiantes

Este es un proyecto de API REST para la gestión de estudiantes en una universidad, desarrollado con **Spring Boot**.

## Tecnologías utilizadas
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA (simulación con `ConcurrentHashMap` en lugar de base de datos)
- Lombok
- Maven
- Postman (para pruebas de API)

## Instalación y Ejecución
1. Clonar este repositorio:
   ```bash
   git clone https://github.com/tuusuario/gestion-estudiantes.git
   cd gestion-estudiantes
   ```
2. Compilar y ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints Disponibles

### Estudiantes
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| GET | `/api/estudiantes` | Obtener todos los estudiantes |
| GET | `/api/estudiantes/{id}` | Obtener un estudiante por ID |
| POST | `/api/estudiantes` | Crear un nuevo estudiante |
| PUT | `/api/estudiantes/{id}` | Actualizar un estudiante |
| DELETE | `/api/estudiantes/{id}` | Eliminar un estudiante |

## Pruebas con Postman
Puedes importar el siguiente JSON en Postman para probar los endpoints:
```json
{
  "info": {
    "name": "Gestion Estudiantes API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Obtener todos los estudiantes",
      "request": {
        "method": "GET",
        "url": "http://localhost:8080/api/estudiantes"
      }
    },
    {
      "name": "Crear estudiante",
      "request": {
        "method": "POST",
        "url": "http://localhost:8080/api/estudiantes",
        "body": {
          "mode": "raw",
          "raw": "{\"nombre\": \"Juan\", \"apellido\": \"Perez\", \"email\": \"juan.perez@example.com\", \"fechaNacimiento\": \"2000-05-15\", \"numeroInscripcion\": \"5001\"}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        }
      }
    }
  ]
}
```

## Autor
**Jesus Alejandro Cruz**  
Email: [jcruzc2@fcpn.edu.bo]

## Desarrollo Web Backend