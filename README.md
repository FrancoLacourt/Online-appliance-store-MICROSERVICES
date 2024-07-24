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
