# ReparaTec API

API REST de **ReparaTec**, una plataforma para recuperar, reparar y donar equipos tecnológicos
(computadoras, tabletas y teléfonos) a estudiantes, adultos mayores y familias que los necesitan.
Ciudadanos y empresas registran donaciones, los técnicos reparan los equipos y los beneficiarios
solicitan uno.

Construida con Spring Boot y Gradle. En este parcial los datos viven en memoria, dentro de
estructuras de datos propias; la base de datos solo se conecta y se comprueba desde un endpoint.

## Integrantes

- Manuel Rodríguez Moran
- Marco Patricio Gutiérrez Amor

## Cómo correr el proyecto

1. Crea un archivo `.env` en la raíz copiando `.env.example`, y llena los datos de tu base de datos:

```
DB_URL=jdbc:postgresql://tu-servidor:5432/nombre_base_de_datos
DB_USER=tu_usuario
DB_PASSWORD=tu_password
```

2. Corre el proyecto:

```
./gradlew bootRun
```

En Windows: `gradlew.bat bootRun`

3. La API queda en http://localhost:8080. Comprueba que funciona en http://localhost:8080/api/health

## Estructura del proyecto

Dentro de `src/main/java/com/example/parcial3`:

| Paquete | Qué contiene |
| --- | --- |
| `controllers` | Reciben las peticiones y regresan las respuestas. No guardan datos. |
| `services` | La lógica y los datos en memoria, dentro de nuestras estructuras. |
| `models` | Cómo son los datos del sistema (`Usuario`, `Equipo`...). |
| `dto` | Lo que manda el cliente al crear un registro (`CreateUsuarioRequest`...). No llevan id. |
| `structures` | Nuestras estructuras de datos propias. |

Los services se reciben en los controllers por inyección de dependencias por constructor.

## Módulos y endpoints

Cada módulo empieza con 3 registros de ejemplo.

| Módulo | Método | Ruta | Respuesta |
| --- | --- | --- | --- |
| Usuarios | GET | `/api/usuarios` | 200 con la lista |
| | GET | `/api/usuarios/{id}` | 200 con el usuario, o 404 |
| | POST | `/api/usuarios` | 201 con el usuario creado |
| Donadores | GET | `/api/donadores` | 200 con la lista |
| | GET | `/api/donadores/{id}` | 200 con el donador, o 404 |
| | POST | `/api/donadores` | 201 con el donador creado |
| Equipos | GET | `/api/equipos` | 200 con la lista |
| | GET | `/api/equipos/{id}` | 200 con el equipo, o 404 |
| | POST | `/api/equipos` | 201 con el equipo creado |
| Técnicos | GET | `/api/tecnicos` | 200 con la lista |
| | GET | `/api/tecnicos/{id}` | 200 con el técnico, o 404 |
| | POST | `/api/tecnicos` | 201 con el técnico creado |
| Componentes | GET | `/api/componentes` | 200 con la lista |
| | GET | `/api/componentes/{id}` | 200 con el componente, o 404 |
| | POST | `/api/componentes` | 201 con el componente creado |
| Solicitudes | GET | `/api/solicitudes` | 200 con todas las solicitudes |
| | GET | `/api/solicitudes/pendientes` | 200 con la cola de pendientes, en orden de atención |
| | GET | `/api/solicitudes/{id}` | 200 con la solicitud, o 404 |
| | POST | `/api/solicitudes` | 201 con la solicitud creada (entra al final de la cola) |
| | POST | `/api/solicitudes/atender` | 200 con la siguiente solicitud atendida, o 404 si no hay pendientes |
| Health | GET | `/api/health` | `OK` |
| | GET | `/api/health/db` | `Database OK` si la consulta `SELECT 1` funciona |

Si a un POST le falta su campo principal, responde 400.

## Estructuras de datos

| Estructura | Archivos | Dónde se usa | Por qué |
| --- | --- | --- | --- |
| Lista doblemente ligada (propia, con nodos) | `DoublyLinkedList`, `DoublyNode` | En los 6 services: guarda los registros de Usuarios, Donadores, Equipos, Técnicos, Componentes y Solicitudes | Agrega al final sin recorrer (gracias a `tail`) y permite eliminar reconectando el nodo anterior y el siguiente |
| Cola (propia, con nodos) | `LinkedQueue`, `Node` | En `SolicitudService`: las solicitudes pendientes de equipo | Las solicitudes se atienden en orden de llegada (FIFO): la que lleva más tiempo esperando sale primero |

Spring no sabe convertir nuestras estructuras a JSON, así que las dos tienen un método `toList()`
que copia sus datos a una `List` de Java para responder.

## Colección de Postman

En la carpeta `postman` está `ReparaTec.postman_collection.json` (formato Collection v2.1),
con una carpeta por módulo y un body de ejemplo en cada POST. Usa la variable de colección
`baseUrl` con el valor `http://localhost:8080`.

Para usarla: en Postman, **Import** → elige el archivo → con la API corriendo, envía cualquier request.