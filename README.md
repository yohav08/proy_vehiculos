# Proyecto Registro de Vehículos

Aplicación de escritorio en Java con arquitectura **MVC**, diseñada para registrar y gestionar información de **vehículos** (carros y motos) y sus **propietarios**.

## 🚀 Funcionalidades

- Registro de datos del propietario y vehículo por tipo (moto o auto).
- Generación automática de un **PDF** con códigos QR y de barras.
- Al registrar, los datos se guardan tanto en la **base de datos (MySQL)** como en un **archivo `.txt`**.
- Consulta de registros con opción de **actualizar**, **eliminar** y **filtrar por tipo**.
- Visualización del **total recaudado** por motos, autos y general.
- Visualización de la **fecha actual** mediante **hilos (threads)**.
- Opción para salir de la aplicación de forma segura.

## 🗄️ Base de Datos

- La base de datos se encuentra en el archivo `recaudo.sql` incluido en el proyecto.
- Se requiere tener instalado **XAMPP**, con los servicios **Apache** y **MySQL** activos.
- Acceder a **phpMyAdmin** desde el navegador (`http://localhost/phpmyadmin`) e importar `recaudo.sql`.

## 📚 Documentación

El proyecto está **completamente documentado**, con comentarios en cada clase, método y funcionalidad. Desde la lógica de conexión a la base de datos hasta la interfaz gráfica y generación de archivos, cada parte del código incluye explicaciones que facilitan su comprensión y mantenimiento.

## 🎓 Contexto Académico

Este proyecto fue desarrollado durante el **quinto semestre** de la **Tecnología en Sistematización de Datos**, como entrega final para la asignatura **Programación Multinivel**. En él se aplican todos los conceptos aprendidos a lo largo del semestre, incluyendo diseño modular, persistencia de datos, hilos, generación de archivos y uso del patrón **Modelo-Vista-Controlador**.

## 🛠️ Tecnologías

- Java (Swing)
- MySQL (XAMPP/phpMyAdmin)
- Almacenamiento en archivos `.txt`
- Generación de archivos PDF, QR y códigos de barras
- Hilos (Threads)
- Patrón de diseño MVC

---

Desarrollado por **Yohana Avila** 💡
