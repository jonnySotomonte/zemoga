Este proyecto es un API REST que cuenta con 2 endpoints:

1. Obtener la información del perfil de un usuario listando 5 de sus tweets (GET)
2. Actualizar la información del perfil de un usuario (PUT)

**Prerrequsitos**

Para ejecutar esta aplicación es necesario tener los siguientes elementos:

1. Instalar Java 11
2. Instalar Maven 3.x.x
3. Definir en las variables de entorno del sistema la variable JAVA_HOME
4. Agregar a la variable de entorno del sistema PATH la variable JAVA_HOME
5. Definir la variable de entorno del sistema M2_HOME
6. Instalar Git


**Ejecución**

1. Abrir una terminal de la consola
2. Clonar el repositorio con el comando `git clone`
3. Ejecutar el comando: `cd zemoga`
4. Construir el proyecto con el comando `mvn clean install`
5. Ejecutar el proyecto con el comando `mvn spring-boot:run`


**Consumo**

Al ejecutar el comando que arranca el proyecto la aplicación esta lista para recibir
las peticiones en el puerto `8080`. Para realizar el consumo es reomendado tener un 
cliente REST, como por ejemplo `Postman` o `Insomnia`, otra alternativa es consumir
la apliación mediante el comando `curl` con su respectiva configuración.

GET:

Para consumir el metodo GET se debe construir la siguiente url: `http://localhost:8080/profile/{userName}`
Debe reemplazar la variable `{userName}` por uno de los nombres de usuario almacenados en la Base de Datos,
dicho nombre corresponde a alguno de los valores de la columna `title` de la tabla `portfolio`

Cuando la ejecución del endpoint es exitosa se obtiene la siguiente respuesta:

- Codigo de Respuesta = 200
- Cuerpo de la respuesta = {  
    userName: <nombre del usuario>,  
    imageUrl: <url de la imagen del perfil>    
    description: <descripcion del perfil del usuario>  
    tweets: [  
       <twwet numero 1>,  
       <twwet numero 2>,  
       <twwet numero 3>,  
       <twwet numero 4>,  
       <twwet numero 5>,  
    ]  
}


Cuando la ejecución del endpoint falla se obtiene la siguiente respuesta:

- Codigo de respuesta = 404 o 500
- Cuerpo de la respuesta = <Descripción del error obtenido>


PUT:

Para consumir el metodo PUT se debe construir la siguiente url: `http://localhost:8080/profile/{userName}`
Debe reemplazar la variable `{userName}` por uno de los nombres de usuario almacenados en la Base de Datos,
dicho nombre corresponde a alguno de los valores de la columna `title` de la tabla `portfolio`

En el cuerpo de la petición debe enviar un reuqest así:

{  
description: "nueva descripcion del perfil del usuario"  
}

`De acuerdo a la recomendación hecha en el enunciado de la prueba, en este proyecto solo es permitido
modificar la descripción del perfil, los otros datos no se permiten modificar con el proposito de no 
afectar el funcionamiento para los registros ya existentes`

Cuando la ejecución del endpoint es exitosa se obtiene la siguiente respuesta:

- Codigo de Respuesta = 200

Cuando la ejecución del endpoint falla se obtiene la siguiente respuesta:

- Codigo de respuesta = 500
- Cuerpo de la respuesta = <Descripción del error obtenido>





