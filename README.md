# 🐶 Automatización de API - Pet Store
Este proyecto contiene un conjunto de pruebas automatizadas para la Pet Store API de Swagger (https://petstore.swagger.io). Las pruebas están diseñadas para verificar las funcionalidades clave relacionadas con la gestión de usuarios, mascotas y órdenes de compra, simulando un flujo de usuario típico.

# 🎯 Objetivo
El objetivo principal de este proyecto es automatizar los siguientes escenarios de prueba utilizando Rest Assured y TestNG:

- Creación de un usuario: Verificar la capacidad de registrar un nuevo usuario en la tienda.
- Inicio de sesión (Login): Confirmar que el usuario recién creado puede iniciar sesión exitosamente.
- Listado de mascotas disponibles: Obtener y validar la lista de mascotas con estado "available".
- Consulta de una mascota específica: Recuperar los detalles de una mascota individual por su ID.
- Creación de una orden de compra: Realizar una compra para una mascota específica.
- Cierre de sesión (Logout): Verificar que el usuario pueda cerrar su sesión correctamente.

Después de ejecutar el comando mvn clean test, verás la salida directamente en tu consola.

# ⚠️ Consideraciones Adicionales
Las pruebas están diseñadas para el "happy path" (flujo exitoso). Para una cobertura completa, se podrían añadir escenarios de error y casos borde.

La API de Pet Store es una API de prueba pública. Si experimentas interrupciones o cambios, esto podría afectar la ejecución de las pruebas.
# 🚀 Tecnologías y Dependencias
Este proyecto está construido con las siguientes tecnologías y bibliotecas:

Java 11+: Lenguaje de programación principal. (Asegúrate de especificar la versión que realmente usaste, aquí puse 11+ como recomendación).
Apache Maven: Gestor de dependencias y herramienta de construcción del proyecto.
Rest Assured: Biblioteca Java DSL para simplificar las pruebas de servicios REST.
TestNG: Marco de pruebas (Test Runner) para la ejecución y organización de los tests.
Lombok: Herramienta para reducir el código repetitivo (boilerplate) en los POJOs.
Hamcrest: Framework de "matchers" para aserciones más legibles.
Jackson Databind: Librería para serialización/deserialización de JSON a objetos Java.
Las dependencias están configuradas en el archivo pom.xml.

# ⚙️ Configuración del Proyecto
Para poder ejecutar estas pruebas en tu entorno local, asegúrate de cumplir con los siguientes requisitos y seguir los pasos de configuración:

Requisitos Previos:
Java Development Kit (JDK) 11 o superior instalado y configurado en tu PATH.
Apache Maven 3.6.0 o superior instalado y configurado en tu PATH.
Conexión a internet estable para acceder a la Pet Store API.

# 🏃 Ejecución de las Pruebas
Una vez configurado el proyecto, puedes ejecutar las pruebas de la siguiente manera:

Ejecutar todos los tests: Para compilar y ejecutar todas las pruebas automatizadas, utiliza el siguiente comando Maven:
Bash

...
mvn clean test
...

El comando clean asegura que cualquier compilación anterior sea eliminada.

El comando test ejecuta las pruebas definidas en el proyecto, utilizando testng.xml como suite de pruebas principal.
