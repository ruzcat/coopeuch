# coopeuch
Desafío Técnico

La solución expone los servicios REST desde la ruta raiz `/tareas`:

```bash
http://localhost:8686/tareas
```
Este es un servico de inicio responde un JSON con la bienvenida para no arrojar un error page
Ejemplo

```json
{
  "codigoRespuesta": 0,
  "razon": "Bienvenido",
  "tiempoRespuesta": 0,
  "resultado": true
}
```

*Nota*:
En el archivo application.properties se encuentra la configuracion del puerto por el cual se levanta la solución el cual es `8686` esto por si el puerto `8080` se encuentre ocupado por otra app ya que es un puerto de uso común

## Servicios Disponibles

Para agregar una nueva tarea
```bash
http://localhost:8686/tareas/create/json
```

Para buscar/leer una tarea
```bash
http://localhost:8686/tareas/read/json
```

Para actualizar una tarea
```bash
http://localhost:8686/tareas/update/json
```

Para eliminar una tarea
```bash
http://localhost:8686/tareas/delete/json
```

Para listar todas las tareas
```bash
http://localhost:8686/tareas/findAll/json
```

## Detalle de los sistemas

Spring-Boot STS 4
Maven 3.6.3
Java 8
io.springfox 2.9.2 para Swagger2 (OpenApi 2.0)

## Documentación y consumir el Api REST
Se utilizó la herramienta Swagger para probar y ver la documentación de la solucion (una vez que se levanta el API), esta se despliega en la siguien ruta

```bash
http://localhost:8686/swagger-ui.html
```

para ver el formato JSON de la documentación
```bash
http://localhost:8686/v2/api-docs
```

Para consumir los servicio se debe invocar la URL de acuerdo a la accion deseada o por medio del propio SWAGGER

Ejemplos:
```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8686/tareas/findAll/json'
```
```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8686/tareas/read/json?idTarea=1'
```

## Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio `desafiouno` ejecutar el siguiente comando `maven`

```bash
mvn clean package
```

Luego de compilar el proyecto ingresar al directorio `target` ejecutar el siguiente comando `java`

```bash
java -jar .\coopeuch-1.0.jar
```
*Nota*:
Debe estar disponible el puerto *8686* en el PC para que se ejecute esta API

## Application Properties
La configuración de la conexión a la base de datos `(postgresql)` debe ser actualizada con las credenciales locales de la PC donde se ejecutara el API REST

```bash
#Puerto
server.port=8686

#BD CONFIG
spring.datasource.url=jdbc:postgresql://localhost/coopeuch?useSSL=false
spring.datasource.username=postgres
spring.datasource.password=devru
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto= none
logging.level.org.hibernate.SQL=debug
```

## Script Postgresql tabla tarea
La estructura de la tabla fue creada con el manejador postgresql

```bash
CREATE TABLE public.tarea (
	id_tarea serial NOT NULL,
	descripcion varchar NOT NULL DEFAULT 'SIN INFORMACION'::character varying,
	fecha_creacion timestamp(0) NOT NULL DEFAULT now(),
	vigente bool NOT NULL DEFAULT true,
	CONSTRAINT tarea_pk PRIMARY KEY (id_tarea)
);
```

## Datos ejemplo tabla tarea
```bash
INSERT INTO public.tarea (descripcion,fecha_creacion,vigente) VALUES
	 ('tarea a','2020-12-29 23:32:56',true),
	 ('Spring MVC','2020-12-31 16:37:48',true),
	 ('Spring Boot','2020-12-31 16:38:02',true),
	 ('Maven','2020-12-31 16:38:13',true),
	 ('Pruebas unitarias','2020-12-31 16:38:22',true),
	 ('Deploy','2020-12-31 16:38:35',false);
```