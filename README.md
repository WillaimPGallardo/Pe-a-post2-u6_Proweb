MVC Productos - Autenticación, Validaciones e i18n

Descripción

Aplicación web desarrollada con **Jakarta EE (Servlets + JSP)** que implementa el patrón **MVC** para la gestión de productos.
El sistema incluye **autenticación de usuarios mediante sesión**, **validaciones en servidor** y **soporte de internacionalización (i18n)**, cumpliendo con los requerimientos del laboratorio.

---

Tecnologías

* Java 17
* Jakarta Servlet API
* JSP + JSTL
* Apache Maven
* Apache Tomcat

---

structura

```text
mvc-productos/
 ├── model/
 ├── service/
 ├── controller/
 ├── WEB-INF/views/
 └── resources/
```

---

 Ejecución

1. Compilar:

```bash
mvn clean package
```

2. Copiar:

```text
target/mvc-productos-1.0-SNAPSHOT.war
```

3. Pegar en:

```text
apache-tomcat/webapps/
```

4. Ejecutar Tomcat y abrir:

```text
http://localhost:8080/mvc-productos-1.0-SNAPSHOT/login
```

---
<img width="1054" height="820" alt="image" src="https://github.com/user-attachments/assets/49bcf5d3-1397-4de6-b86d-08a93b206e10" />

 Funcionalidades

* Autenticación con `HttpSession`
* Protección de rutas (`/productos`)
* CRUD de productos
* Validaciones en servidor
* Internacionalización (ES / EN)

---


