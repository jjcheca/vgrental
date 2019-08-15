# vgrental
Video Game Rental System

Para ejecutar el proyecto hay que:

1) Descargarse el proyecto a un directorio local.
2) En el directorio raiz del proyecto ejecutar mvn clean install
3) En el directorio target se habrá generado el fichero vgrental-0.0.1-SNAPSHOT.jar
4) Ejecutamos con java -jar vgrental-0.0.1-SNAPSHOT.jar

Tendremos la aplicación spring-boot con el servicio REST en el Tomcat escuchando en el puerto 8080

En la URL http://localhost:8080/h2 podremos acceder a la base de datos en memoria H2.
Se han cargado algunos datos para la tabla de usuarios y de juegos.

Para probar el servicio he usado Swagger, aunque no en todos los métodos de todos los servicios me ha dado tiempo a afinar la parametrización.

Los JUnit he hecho algunos básicos usando Mockito, he dado más prioridad a dejar funcionando el sistema.
En cuanto a Docker no he podido hacer nada, por falta de tiempo, aunque pretendía poner en acción lo que había estado aprendiendo:

http://www.littlebigextra.com/how-to-create-a-spring-boot-mvc-microservice-with-docker/

