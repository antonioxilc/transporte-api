# transporte-api
Transporte API - Gestión de Órdenes 
API REST desarrollada con Spring Boot para la gestión de órdenes de transporte. 
Descripción 
Esta API permite crear, consultar y actualizar órdenes de transporte. 
Está diseñada siguiendo buenas prácticas de arquitectura en capas, uso de DTOs 
y manejo centralizado de errores. 
Tecnologías utilizadas 
 Java 17 
 Spring Boot 
 Spring Data JPA (Hibernate) 
 PostgreSQL 
 Swagger (OpenAPI) 
 Maven 
Arquitectura 
El proyecto sigue una arquitectura en capas: 
 Controller → Manejo de endpoints 
 Service → Lógica de negocio 
 Repository → Acceso a datos 
 DTO → Transferencia de datos 
 Exception → Manejo global de errores 
Cómo ejecutar el proyecto 
1. Clonar el repositorio: 
git clone https://github.com/TU_USUARIO/ordenes-de-transportet-api.git 
2. Entrar al proyecto: 
cd ordenes-de-transporte 
3. Configurar base de datos PostgreSQL en application.yml 
4. Ejecutar: 
mvn clean install 
mvn spring-boot:run 
Endpoints disponibles 
Crear orden 
POST /orders 
{ 
} 
"origin": "CDMX", 
"destination": "Puebla" 
Listar órdenes 
GET /orders 
�
� Obtener por ID 
GET /orders/{id} 
Actualizar orden 
PUT /orders/{id} 
{ 
} 
"origin": "Veracruz", 
"status": "IN_PROGRESS" 
Manejo de errores 
Se implementa un manejo global de excepciones mediante 
@RestControllerAdvice. 
Ejemplo: 
{ 
"message": "Orden no encontrada" 
} 
Documentación Swagger 
Disponible en: 
http://localhost:8080/swagger-ui/index.html 
Decisiones técnicas 
 Se utilizaron DTOs para desacoplar la capa de presentación de la lógica de 
negocio. 
 Se implementó manejo de errores centralizado. 
 Se optó por mapeo manual en lugar de MapStruct para asegurar estabilidad 
en la entrega. 
 Código preparado para escalar y añadir nuevas funcionalidades. 
Autor 
Antonio Xilcahua Lopez
