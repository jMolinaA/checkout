# Retail Checkout API

Proyecto Java basado en **Spring Boot** que expone una API REST para calcular el checkout de un carrito de compras, aplicando descuentos y promociones.

---

## Tecnologías

- Java 17+
- Spring Boot
- Maven
- Spring Web
- Jackson

---

## Instrucciones Windows

### Compilar el proyecto
`./mvnw clean install`

### Ejecutar la aplicación
`./mvnw spring-boot:run`

## Instrucciones MacOS/Linux

### Dar permisos al wrapper
`chmod +x mvnw`

### Compilar el proyecto
`./mvnw clean install`

### Ejecutar la aplicación
`./mvnw spring-boot:run`


## Ejemplo de request

**Endpoint**

POST /api/v1/checkout
Content-Type: application/json

**Body**

```json
{
  "cartId": "cart-1001",
  "items": [
    { "sku": "SKU-026", "quantity": 1 },
    { "sku": "SKU-014", "quantity": 6 },
    { "sku": "SKU-018", "quantity": 1 },
    { "sku": "SKU-002", "quantity": 2 },
    { "sku": "SKU-041", "quantity": 4 }
  ],
  "shippingAddress": {
    "street": "Av. Falsa 123",
    "city": "Ciudad",
    "zoneId": "zone-1"
  },
  "paymentMethod": "CASH"
}
```

## Decisiones de diseño

- Se optó por utilizar una serie de archivos JSON en lugar de una base de datos real, ya que para el alcance de este proyecto no se vio como necesario. Sin embargo, el proyecto está diseñado de manera de que sea facil añadir una fuente de datos sin tener que realizar modificaciones mayores.

- Se decidió clasificar tanto las promociones como los descuentos en porcentaje y descuento fijo, para facilitar el desarrollo.

## Trade-offs

- El sistema de descuentos y promociones tiene mucho margen de mejora, ya que se puede realizar un motor de descuentos y promociones que pueda tener en cuenta casos como promociones de tipo combo (comprar una serie de productos específicos para obtener un descuento), descuentos por cliente frecuente, entre otros. No se añadieron esos y más casos para no complejizar demasiado la solución.