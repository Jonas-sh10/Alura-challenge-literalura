## <h1 align="center"><span id="título-portada">LiterAlura: Challenge Java</span> </h1>
___
![Imagen](https://images.unsplash.com/photo-1521587760476-6c12a4b040da?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D)

___
## <span id="insignias">Insignias</span>
![Proyecto](https://img.shields.io/badge/Status-Main_Functions_Finished-brightgreen)
![IDE](https://img.shields.io/badge/IDE-Intellij_IDEA-ff69b4)
![Java JDK](https://img.shields.io/badge/Java_JDK-v17.0-1e90ff)
![Project](https://img.shields.io/badge/Project-Maven-ff4500)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v3.3.0-6a5acd)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-v16.2-ffa500)
![Jackson](https://img.shields.io/badge/Jackson-v2.16.0-lightcoral)


___
## <span id="índice">Índice</span>

* [Título e imagen de portada](#título-portada)
* [Insignias](#insignias)
* [Índice](#índice)
* [Descripción del proyecto](#descripción-del-proyecto)
* [Características de la aplicación](#características-de-la-aplicación)
* [Tecnologías utilizadas](#tecnologías-utilizadas)
* [Acceso al proyecto](#acceso-al-proyecto)

---
## <span id="descripción-del-proyecto">Descripción del proyecto</span>
<p align="justify">
 En este emocionante desafío de programación en Java, se construye un catálogo de libros: LiterAlura. Este proyecto tiene como objetivo principal proporcionar una experiencia interactiva a los usuarios a través de una interfaz de consola, permitiéndoles buscar libros mediante la API de Gutendex, guardar estos datos en una base de datos de PostgreSQL y realizar diversas consultas.
</p>

---
## <span id="características-de-la-aplicación">Principales Funciones de la Aplicación</span>
<p align="justify">
    <span style="color:#1E90FF;"><strong>1. Buscar Libro:</strong></span> Permite buscar libros utilizando la API de Gutendex y guardar el primer resultado encontrado en la base de datos.<br>

* Se puede buscar por titulo de la obra o nombre del autor.
* No se puede guardar dos libros o autores repetidos en la base de datos.

</p>
<p align="justify">
    <span style="color:#32CD32;"><strong>2. Listar Libros:</strong></span> Imprime por consola todos los libros guardados en la base de datos.<br>

</p>
<p align="justify">
    <span style="color:#FF6347;"><strong>3. Listar Autores:</strong></span> Muestra todos los autores guardados en la base de datos.<br>

</p>
<p align="justify">
    <span style="color:#8A2BE2;"><strong>4. Listar Autores por Año:</strong></span> Imprime los autores que estaban vivos en el año especificado.<br>

</p>
<p align="justify">
    <span style="color:#FFA500;"><strong>5. Listar Libros por Idioma:</strong></span> Filtra los libros según el idioma especificado y los muestra por consola.

* Si se desea agregar más idiomas, ve a "<u>enum Idioma</u>" y aumente los idiomas que se desea.
</p>

---
## <span id="tecnologías-utilizadas">Tecnologías utilizadas</span>

### Pricipales Requisitos
<p align="justify">

1. **Java JDK**: Versión 17 o superior
   - [Download the Latest Java LTS Free](https://www.oracle.com/java/technologies/javase-downloads.html)
</p>
<p align="justify">

2. **Maven**: Versión 4 o superior para la gestión de dependencias y construcción del proyecto.
   - [Apache Maven](https://maven.apache.org/download.cgi)
</p>
<p align="justify">

3. **Spring Boot**: Versión 3.3.0.
   - [Spring Initializr](https://start.spring.io/)
</p>
<p align="justify">

4. **PostgreSQL**: Versión 14.12 o superior como base de datos relacional.
   - [PostgreSQL](https://www.postgresql.org/download/)
</p>
<p align="justify">

5. **IDE**: IntelliJ IDEA para el desarrollo integrado del proyecto.
   - [JetBrains IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/)
</p>

### Dependencias del proyecto
<p align="justify">

- **Spring Data JPA**: Para la integración con la capa de persistencia.
- **Postgres Driver**: Para la conexión con la base de datos PostgreSQL.
- **Jackson**: Para el manejo de JSON.
</p>

### Configuración de la Base de Datos
<p align="justify">

- Para configurar la conexión a la base de datos PostgreSQL, añade las siguientes propiedades a tu archivo `application.properties` en tu proyecto Spring Boot:
</p>

```properties
spring.datasource.url = jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username = ${DB_USER}
spring.datasource.password = ${DB_PASSWORD}
```

---
## <span id="acceso-al-proyecto">Acceso al proyecto</span>
-  Clonar el repositorio
```bash
git clone https://github.com/Jonas-sh10/Alura-challenge-literalura.git
