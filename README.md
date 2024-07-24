PROYECTO MICROSERVICIOS.

Este proyecto es acerca de una tienda online. Está hecho con una arquitectura de microservicios, que son los siguientes:
- Productos.
- Carrito de compras.
- Usuarios.
- Venta.

Venta consume al servicio de Usuarios, Usuarios consume al servicio de Carrito de compras, Carrito de compras consume al servicio de productos.

El flujo sería el siguiente:
Cuando se crea un usuario, se crea automáticamente un carrito (consumiendo al servicio Carrito de compra) que se asocia al mismo. Cada usuario tiene un único carrito. A este carrito de compras, 
se le puede ir agregando productos (que ya fueron cargados previamente por la tienda, con su respectivo stock) consumiendo al servicio Productos. Y una vez que se crea la venta (o sea, que se desea finalizar 
la compra) consumiendo al servicio Usuarios, se registra el momento en el que se genera la venta y se muestra el detalle de lo que se compró, cuántas unidades de cada producto, el precio de cada uno, 
el precio total, etc. y finalmente el carrito se vacía.

En este proyecto, utilicé los siguientes patrones de diseño:
- Service Registry y Service Discovery (Eureka Server)
- Load Balancing (Spring Cloud Load Balancer)
- Circuit Breaker (Resilience4J)
- API Gateway (Spring Cloud Gateway)
- Config Server

Y todos estos microservicios, fueron empaquetados y desplegados en Docker. Utilizando herramientas como docker-compose, y Dockerfile en cada servicio.

Aclarar que este proyecto está enfocado principalmente en el uso de la arquitectura de Microservicios.


-------------------------------------------------------------------------------------------------------------------------------------------------------

MICROSERVICES PROJECT
This project is about an online store. It is built with a microservices architecture, consisting of the following services:

Products
Shopping Cart
Users
Sales
The Sales service consumes the Users service, the Users service consumes the Shopping Cart service, and the Shopping Cart service consumes the Products service.

Flow:
When a user is created, a shopping cart is automatically created (by consuming the Shopping Cart service) and associated with the user. Each user has a unique shopping cart. Products (previously loaded by the store with their respective stock) can be added to this shopping cart by consuming the Products service. Once a sale is created (i.e., the purchase is finalized) by consuming the Users service, the time of the sale is recorded, and the details of the purchase are displayed, including the number of units of each product, the price of each, the total price, etc., and finally, the shopping cart is emptied.

Design Patterns Used:
In this project, the following design patterns were utilized:

Service Registry and Service Discovery (Eureka Server)
Load Balancing (Spring Cloud Load Balancer)
Circuit Breaker (Resilience4J)
API Gateway (Spring Cloud Gateway)
Config Server
All these microservices were packaged and deployed using Docker, with tools like docker-compose and Dockerfile for each service.

Please note that this project is primarily focused on the use of microservices architecture.
