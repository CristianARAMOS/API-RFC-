API para gestión y generación de RFC (Registro Federal de Contribuyentes) con documentación interactiva mediante Swagger.


1. Configuración de Variables de Entorno
Copia el archivo .env.template a .env y configura los valores:
Luego edita el archivo .env con tus credenciales.


Construir la imagen Docker
docker build -t api-rfc .

# Ejecutar el contenedor en el puerto 8080
docker run -p 8080:8080 --env-file .env api-rfc
3. Acceso a la Aplicación
API Principal: http://localhost:8080

Documentación Swagger: http://localhost:8080/swagger-ui.html

Consola H2 Database: http://localhost:8080/h2-console

Endpoints Principales
GET /api/v1/rfc/{data} - Buscar persona por RFC, nombre o fecha o obtener todos 

POST /api/v1/rfc/generar - Generar RFC a partir de datos personales
