# MI PRIMERA API REST CON SPRING BOOT 💻
Dependecias usadas
- Mysql connector
- Spring web
- Spring JPA
- Loombok
- Spring validation
## Dependencia de validacion

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
## 📌 Anotaciones de Validación Spring Boot

| Tipo de Dato       | Anotación                | Descripción                                                                 | Ejemplo                                  |
|--------------------|--------------------------|-----------------------------------------------------------------------------|------------------------------------------|
| **Genéricas**      | `@NotNull`               | Campo no puede ser null                                                     | `@NotNull String id;`                    |
|                    | `@Valid`                 | Valida objetos anidados                                                     | `@Valid Address address;`                |
| **String**         | `@NotBlank`              | No null, no vacío, sin espacios                                             | `@NotBlank String username;`             |
|                    | `@NotEmpty`              | No null y no vacío                                                          | `@NotEmpty String password;`             |
|                    | `@Size(min, max)`        | Longitud entre min y max                                                    | `@Size(min=3, max=20) String code;`      |
|                    | `@Pattern(regex)`        | Cumple expresión regular                                                    | `@Pattern(regexp="[A-Z]+") String name;` |
|                    | `@Email`                 | Formato email válido                                                        | `@Email String email;`                   |
| **Números**        | `@Min(value)`            | Valor mínimo (≥)                                                            | `@Min(1) int quantity;`                  |
|                    | `@Max(value)`            | Valor máximo (≤)                                                            | `@Max(100) int discount;`                |
|                    | `@Positive`              | Número > 0                                                                  | `@Positive double price;`                |
|                    | `@PositiveOrZero`        | Número ≥ 0                                                                  | `@PositiveOrZero long total;`            |
|                    | `@Negative`              | Número < 0                                                                  | `@Negative int balance;`                 |
|                    | `@NegativeOrZero`        | Número ≤ 0                                                                  | `@NegativeOrZero double delta;`          |
| **Boolean**        | `@AssertTrue`            | Debe ser true                                                               | `@AssertTrue boolean isActive;`          |
|                    | `@AssertFalse`           | Debe ser false                                                              | `@AssertFalse boolean isDeleted;`        |
| **Fechas**         | `@Past`                  | Fecha en pasado                                                             | `@Past LocalDate birthDate;`             |
|                    | `@PastOrPresent`         | Fecha en pasado/presente                                                    | `@PastOrPresent LocalDateTime update;`   |
|                    | `@Future`                | Fecha en futuro                                                             | `@Future LocalDate expiration;`          |
|                    | `@FutureOrPresent`       | Fecha en futuro/presente                                                    | `@FutureOrPresent LocalDateTime event;`  |
| **Colecciones**    | `@NotEmpty`              | No null y no vacío                                                          | `@NotEmpty List<String> items;`          |
|                    | `@Size(min, max)`        | Tamaño entre min y max                                                      | `@Size(min=1) Set<Long> ids;`            |

## 🔹 Ejemplo de Uso

```java
public class UserDTO {
    @NotBlank(message = "Nombre requerido")
    private String name;
    
    @Email
    private String email;
    
    @Size(min = 8)
    private String password;
    
    @Min(18)
    private int age;
}
````
## 🖨️Como mostrar el error con rest api
```java
@GetMapping
public ResponseEntity<?> guardar(@Valid  @RequestBody UserDTO obj){

        if (errores.hasErrors()) {
            Map<String, String> mapeoErrores =
            errores.getFieldErrors()  
            .stream() //ES MEJOR USAR CON STREAM EN MI OTRO RESCONTROLLER ESTA DESARROLLADO DIFERENTE
            .collect(Collectors.toMap(
                error -> error.getField(), error -> error.getDefaultMessage()));
                    //CAMPO                             //MENSAJE DE ERROR

            return ResponseEntity.badRequest().body(mapeoErrores); //AQUI MANDA EL CAMPO CON EL ERROR A MOSTRAR
        }

        servicio.guardar(obj);
        return ResponseEntity.status(201).build();
    }
}
```

## 📌 Códigos de Estado HTTP (Status Codes)

| Código | Nombre                     | Categoría       | Descripción                                                                 |
|--------|----------------------------|-----------------|-----------------------------------------------------------------------------|
| **1xx** | **Informativo**           | Información     | Petición recibida, continuando proceso.                                    |
| 100    | Continue                   |                 | El servidor acepta la solicitud inicial.                                   |
| 101    | Switching Protocols        |                 | Cambio de protocolo (ej: HTTP → WebSocket).                                |
| **2xx** | **Éxito**                 | Satisfactorio   | Petición recibida, entendida y procesada correctamente.                    |
| 200    | OK                         |                 | Respuesta estándar para peticiones exitosas.                               |
| 201    | Created                    |                 | Recurso creado (usado en POST).                                            |
| 204    | No Content                 |                 | Petición exitosa pero sin contenido en la respuesta.                       |
| **3xx** | **Redirección**           | Redirección     | Se requiere acción adicional para completar la petición.                    |
| 301    | Moved Permanently          |                 | Recurso movido permanentemente a otra URL.                                 |
| 302    | Found                      |                 | Recurso movido temporalmente a otra URL.                                   |
| 304    | Not Modified               |                 | Indica que el recurso no ha sido modificado (usado en caché).              |
| **4xx** | **Error del Cliente**      | Error cliente   | La petición contiene sintaxis incorrecta o no puede procesarse.            |
| 400    | Bad Request                |                 | Solicitud malformada o inválida.                                           |
| 401    | Unauthorized               |                 | Autenticación fallida o no proporcionada.                                  |
| 403    | Forbidden                  |                 | El servidor rechaza la acción (aunque la autenticación fue exitosa).       |
| 404    | Not Found                  |                 | Recurso no encontrado.                                                     |
| 405    | Method Not Allowed         |                 | Método HTTP no permitido para el recurso.                                  |
| **5xx** | **Error del Servidor**     | Error servidor  | El servidor falló al completar una solicitud válida.                       |
| 500    | Internal Server Error      |                 | Error genérico del servidor.                                               |
| 502    | Bad Gateway                |                 | Respuesta inválida de un servidor upstream.                                |
| 503    | Service Unavailable        |                 | Servicio no disponible (generalmente por mantenimiento).                   |
| 504    | Gateway Timeout            |                 | Tiempo de espera agotado entre servidores.                                 |
