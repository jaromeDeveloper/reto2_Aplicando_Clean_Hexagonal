# Reto 2 – API REST con Arquitectura Hexagonal (Ports & Adapters)

**Stack:** Java 17, Spring Boot 3.3.x, Maven multi-módulo. Adapter por defecto: **InMemory**.

## Módulos
```
hexagonal-parent/
├─ domain/                  ➜ Modelos + Ports (in/out)
├─ application/             ➜ Servicios / Use Cases
├─ adapters-in-rest/        ➜ Controller REST (inbound)
├─ adapters-out-inmemory/   ➜ Repo en memoria (outbound)
├─ adapters-out-jpa/        ➜ Repo JPA/H2 (opcional)
└─ boot/                    ➜ Arranque y wiring de beans
```
**Dependencias:** `application -> domain`; `adapters -> application/domain`; `boot -> application + adapters`. El dominio no depende de frameworks.

## Cómo ejecutar
```bash
cd hexagonal-parent
mvn -q clean package

cd boot
mvn -q spring-boot:run
# App en http://localhost:8080
```

## Endpoints y cURL
### Crear (POST)
```bash
curl -X POST http://localhost:8080/api/products   -H "Content-Type: application/json"   -d '{"name":"Monitor 24","price":2799.90}'
```
**200 OK**
```json
{ "id": "UUID", "name": "Monitor 24", "price": 2799.90 }
```
### Obtener por id (GET)
```bash
curl http://localhost:8080/api/products/<UUID>
```
### Listar (GET)
```bash
curl http://localhost:8080/api/products
```

## Pruebas
- **Unitarias (Application):** `ProductServiceTest` sin Spring.
- **Web (REST):** puedes añadir pruebas `@WebMvcTest` en `adapters-in-rest` (base incluida en POM).

## Activar adapter JPA/H2 (opcional)
1) Añade dependencia `adapters-out-jpa` en `boot/pom.xml` (descomenta bloque).  
2) Configura `spring.datasource` en `boot/src/main/resources/application.yml`.  
3) Provee un `@Configuration` alternativo que inyecte `JpaProductRepositoryAdapter`.

## Checklist de evaluación (5 × 20 pts)
- [x] Estructura multi–módulo y referencias Hexagonal.
- [x] Dominio: `Product` + puertos In/Out.
- [x] Aplicación: `ProductService` implementa puertos de entrada.
- [x] Adapters: Inbound REST + Outbound InMemory (JPA opcional).
- [x] Pruebas y cURL para evidencias de ejecución.
