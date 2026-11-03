# eureka-server · LogiTrack

Servidor de **descubrimiento y registro de servicios** (Eureka) de LogiTrack, Sprint 3 de Desarrollo y
Arquitecturas Avanzadas de Software, FI UNJu 2026.

Cada microservicio se registra acá al arrancar y los demás lo encuentran por su **nombre lógico**
(`COURIER-SERVICE`, `SHIPMENT-SERVICE`, `SECURITY-SERVICE`, `API-GATEWAY`), sin conocer IP ni puerto.

| | |
|---|---|
| Lenguaje | Java 25 |
| Framework | Spring Boot 4.0.9 · Spring Cloud 2025.1.1 |
| Puerto | `8065` |
| Panel | http://localhost:8065 |
| Configuración | `eureka-server.yml` en el repositorio `logitrack-config` |

## Configuración

El puerto y las propiedades de Eureka no están en este repositorio: las entrega el **config-server**.
`eureka-server.yml` (en `logitrack-config`) define:

```yaml
server:
  port: 8065

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false   # el servidor no se registra a sí mismo
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

| Variable | Para qué | Por defecto |
|---|---|---|
| `CONFIG_SERVER_URL` | URL del config-server | `http://localhost:8888` |
| `SPRING_PROFILES_ACTIVE` | Perfil de configuración | `dev` |

## Ejecutar

Orden de arranque: primero el `config-server` (8888) y después este servidor.

```bash
./mvnw -B clean verify     # compila y corre los tests (no necesitan config-server)
./mvnw spring-boot:run
```

- Panel: http://localhost:8065
- Health: http://localhost:8065/actuator/health
- Servicios registrados (JSON): `GET http://localhost:8065/eureka/apps` con `Accept: application/json`

## Cómo se registra un servicio

En cada microservicio: la dependencia `spring-cloud-starter-netflix-eureka-client` y, en su configuración
(`logitrack-config`):

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://eureka-server:8065/eureka/   # en dev: http://localhost:8065/eureka/
  instance:
    prefer-ip-address: true
```
