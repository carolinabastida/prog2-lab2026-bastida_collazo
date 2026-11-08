
# Sistema de Solicitudes de Soporte Técnico

**Programación 2 – CeRP del Suroeste**  
**Año:** 2026  
**Docente:** Domingo Pérez

## Integrantes

- Carolina Bastida Haima
- María Collazo

## Presentación del proyecto

Para este trabajo elegimos desarrollar un sistema de solicitudes de soporte técnico, utilizando como base el proyecto Java proporcionado por el profesor.

Nuestra idea es contar con un programa sencillo para registrar problemas relacionados con computadoras, impresoras y conexiones de red, como los que pueden presentarse en un centro educativo.

## Avance del 31 de octubre

En esta primera etapa trabajamos en la gestión de solicitudes, la incorporación de componentes gráficos y el guardado de información en JSON.

El programa permite:

- Registrar nuevas solicitudes.
- Modificar solicitudes existentes.
- Eliminar registros con confirmación.
- Buscar solicitudes y limpiar el formulario.
- Guardar y recuperar la información mediante JSON.

Cada solicitud tiene un identificador, nombre del problema, categoría, estado, descripción, cantidad de equipos, prioridad y solución aplicada.

## Componentes gráficos

Mantuvimos los componentes Swing del proyecto original y agregamos tres tipos nuevos:

- **JTextArea:** para escribir la descripción del problema y la solución aplicada.
- **JSpinner:** para indicar la cantidad de equipos afectados.
- **JRadioButton:** para seleccionar la prioridad alta, media o baja.

Estos componentes se integraron al formulario para registrar y modificar las solicitudes.

## Validaciones

Incorporamos controles para evitar errores al registrar información.

El programa verifica que el identificador sea positivo y no esté repetido, que el nombre del problema no quede vacío y que la cantidad de equipos sea mayor que cero.

También agregamos una regla relacionada con nuestro tema: **no se puede cerrar una solicitud sin indicar la solución aplicada**.

## Organización del programa

Conservamos la estructura MVC y la fachada del proyecto del profesor.

El modelo contiene los datos y las validaciones; la vista muestra la ventana; el controlador recibe las acciones de los botones y la fachada administra las operaciones. La persistencia permite guardar y recuperar los registros mediante JSON.

## Pruebas realizadas

Probamos el programa en Visual Studio Code utilizando Java 25.

Comprobamos que se pueden registrar, modificar, buscar y eliminar solicitudes. También probamos las validaciones y verificamos que aparezcan los mensajes de error correspondientes.

Para comprobar el guardado, registramos varias solicitudes, cerramos el programa y volvimos a abrirlo. Los datos se mantuvieron, incluyendo los cambios realizados.

### Pruebas de validación y persistencia realizadas por María

| Acción realizada | Resultado esperado | Resultado obtenido |
|---|---|---|
| Registrar una solicitud con un identificador repetido | Mostrar un error y evitar duplicados | El programa mostró el mensaje de identificador repetido. |
| Cerrar una solicitud sin indicar la solución aplicada | Impedir el registro e informar el motivo | Apareció el mensaje de error y la solicitud no se agregó. |
| Eliminar una solicitud de prueba | Solicitar confirmación y eliminar el registro | Se confirmó la eliminación y el registro desapareció de la tabla. |
| Iniciar sin el archivo JSON | Abrir la aplicación con una tabla vacía | El programa abrió normalmente y mostró la tabla vacía. |
| Iniciar con un JSON inválido | Mostrar un error sin cerrar la aplicación | El programa informó que no podía cargar los registros y permaneció abierto. |
| Comprobar que el JSON inválido no se sobrescriba | Conservar el archivo defectuoso | El archivo continuó existiendo después de cerrar la aplicación. |

Antes de estas pruebas, guardamos una copia de seguridad del archivo JSON original. Al finalizar, restauramos su nombre y retiramos los archivos temporales del proyecto.


### Pruebas de validación realizadas por Carolina

Para continuar con la revisión del sistema, realicé pruebas sobre algunas de las validaciones que incorporamos durante el desarrollo del proyecto.

Primero comprobé qué sucede cuando se intenta registrar una solicitud con un identificador igual a cero. El programa mostró el mensaje "El identificador debe ser mayor que cero" y no permitió agregar el registro.

Después probé registrar una solicitud dejando vacío el nombre del problema. En este caso, apareció el mensaje "Debe escribir el nombre del problema" y la solicitud tampoco se agregó.

En ambas pruebas, el programa respondió de acuerdo con lo esperado. Los registros originales, identificados con los números 1, 2 y 4, permanecieron sin modificaciones.

Estas comprobaciones permitieron verificar que el sistema controla los datos ingresados y evita guardar solicitudes que no cumplen con las condiciones establecidas.


## Cómo ejecutar el programa

Desde la carpeta del proyecto, ejecutar en la terminal:

`.\mvnw.cmd compile exec:java`

Los datos se guardan en `datos/registros.json`.

## Trabajo de las integrantes

**Carolina Bastida Haima:** trabajó directamente en Visual Studio Code, incorporando las modificaciones del código y realizando las pruebas de funcionamiento, validaciones y guardado JSON.

**María Collazo:** colaboró aportando ideas, revisando la propuesta y realizando sugerencias para mejorar el sistema.

Durante esta etapa intercambiamos ideas sobre el funcionamiento del programa y las características que debía tener.

## Próxima etapa

Para la entrega final del 7 de noviembre continuaremos revisando el programa, realizando las pruebas pendientes y preparando el video explicativo en el que participaremos ambas integrantes.

## Recursos utilizados

Partimos del proyecto Java proporcionado por el profesor Domingo Pérez. Utilizamos Java Swing, Maven y Gson para trabajar con archivos JSON.


## Destinatarios y utilidad del sistema

El sistema está pensado para registrar y organizar solicitudes de soporte técnico en un centro educativo.

Puede ser utilizado por las personas encargadas de atender problemas informáticos relacionados con computadoras, impresoras y conexiones de red.

Su utilidad consiste en reunir la información de cada solicitud en un mismo lugar, consultar los problemas registrados, identificar su prioridad y dejar constancia de la solución aplicada.

## Adaptación del modelo y reglas del sistema

Partimos del gestor de registros proporcionado por el profesor y lo adaptamos a la gestión de solicitudes de soporte técnico.

Además del identificador, nombre, categoría y estado, incorporamos la descripción del problema, la cantidad de equipos afectados, la prioridad y la solución aplicada.

Definimos las categorías Hardware, Software y Red para clasificar las solicitudes.

También incorporamos validaciones para controlar los datos ingresados. El identificador debe ser positivo y no puede repetirse, el nombre del problema es obligatorio y la cantidad de equipos debe ser mayor que cero.

Como regla propia de nuestro proyecto, una solicitud no puede cerrarse sin indicar la solución aplicada.

## Recorrido de una operación por el programa

Para comprender cómo trabajan las distintas partes del sistema, tomamos como ejemplo el registro de una solicitud.

El usuario completa el formulario de la ventana principal y presiona Registrar.

El controlador recibe esa acción, recoge los datos ingresados y utiliza la fachada para solicitar el registro.

El modelo representa la solicitud y comprueba que sus datos cumplan con las validaciones establecidas.

Si los datos son correctos, la fachada incorpora la solicitud y coordina su guardado mediante la clase de persistencia.

Finalmente, la ventana actualiza la tabla para mostrar la nueva solicitud.

Esta organización permite separar la presentación de la información, las operaciones del programa y el almacenamiento de los datos.

## Comprobaciones complementarias del funcionamiento

Además de las pruebas de validación documentadas anteriormente, realizamos las siguientes comprobaciones durante el desarrollo del sistema.

| Acción realizada | Resultado esperado | Resultado obtenido |
|---|---|---|
| Registrar una solicitud válida | Incorporar el registro a la tabla | La solicitud apareció en el listado. |
| Modificar la prioridad de una solicitud | Guardar el cambio sin alterar su identificador | La prioridad se actualizó y se conservó el identificador. |
| Cancelar una eliminación | Conservar el registro | La solicitud permaneció en la tabla. |
| Confirmar la eliminación de un registro de prueba | Eliminar solamente el registro seleccionado | La solicitud de prueba desapareció del listado. |
| Buscar una solicitud | Mostrar los registros que coinciden con la búsqueda | El sistema mostró los resultados correspondientes. |
| Cerrar y volver a abrir el programa | Recuperar los datos guardados | Las solicitudes y sus modificaciones se mantuvieron. |

## Requisitos de ejecución y limitaciones conocidas

El programa se desarrolló y probó en Windows utilizando Java 25.

Para compilarlo y ejecutarlo se utiliza Maven, mediante el archivo `mvnw.cmd` incluido en el proyecto.

La interfaz gráfica está desarrollada con Java Swing y el almacenamiento de datos utiliza archivos JSON mediante la biblioteca Gson.

El sistema funciona como una aplicación de escritorio. La información se guarda localmente en `datos/registros.json` y no se encuentra sincronizada entre diferentes computadoras.

No requiere una base de datos externa ni conexión a Internet para registrar y consultar solicitudes.


## Aportes durante la preparación de la entrega final

María realizó comprobaciones relacionadas con los identificadores repetidos, el cierre de solicitudes, la eliminación de registros y el manejo de archivos JSON ausentes o inválidos. Documentó esas pruebas y publicó su aporte en GitHub.

Carolina comprobó las validaciones de identificador igual a cero y nombre del problema vacío. En ambos casos verificó los mensajes de error y que no se agregaran registros incorrectos. También documentó los resultados y publicó su aporte en GitHub.

Ambas participamos en el desarrollo del proyecto y en la revisión de su funcionamiento.

## Video de presentación y demostración del proyecto

Como parte de la entrega final de Programación II, realizamos un video para presentar nuestro **Sistema de Solicitudes de Soporte Técnico**, desarrollado por Carolina Bastida Haima y María Collazo.

En esta presentación mostramos el funcionamiento de la aplicación y explicamos las principales características del sistema: el registro, la modificación, la búsqueda y la eliminación de solicitudes, así como las validaciones incorporadas para evitar errores en el ingreso de datos.

También presentamos los componentes gráficos utilizados, la organización del código Java mediante el modelo MVC, el funcionamiento de la fachada y el almacenamiento de los registros en archivos JSON.

### Video final de demostración

En este video participamos ambas integrantes, utilizando nuestras propias grabaciones y explicaciones.

**Duración:** 4 minutos y 35 segundos.

[**Ver video final del Sistema de Solicitudes de Soporte Técnico**](https://drive.google.com/file/d/1jJUM83Il3BGLqYPu9iSeJYzlYa4Nr_lY/view?usp=sharing)

### Elaboración del video

Las filmaciones fueron realizadas por nosotras mientras trabajábamos con el programa y comprobábamos su funcionamiento. También grabamos las explicaciones con nuestras propias voces, siguiendo un guion que organizamos para presentar el proyecto de manera clara y sencilla.

Para unir los diferentes fragmentos, organizar las escenas, mejorar la presentación e incorporar algunas capturas del código, utilizamos ChatGPT como herramienta de apoyo para la edición audiovisual.

El contenido, las demostraciones y las explicaciones corresponden a nuestro trabajo. La herramienta nos permitió reunir el material en una presentación más ordenada, conservando nuestras voces y el carácter original de las grabaciones.

### Material complementario: grabaciones caseras originales

Además del video final, decidimos compartir como material extra las grabaciones caseras que realizamos durante las pruebas del programa.

Estas filmaciones muestran de manera más espontánea cómo trabajamos con la aplicación, completamos formularios, realizamos distintas operaciones y comprobamos algunas de las validaciones implementadas.

Las seis grabaciones originales fueron reunidas en un único archivo, conservando su sonido y contenido, sin incorporar narraciones adicionales.

Las incluimos para que también se pueda observar parte del proceso de trabajo y las comprobaciones que fuimos realizando.

**Duración:** 7 minutos y 26 segundos.

[**Enlace al material complementario**](https://drive.google.com/file/d/1q5LBEpSXgJ-XN9yTiYUTFoUtpxEIxpeK/view?usp=sharing)

**Estas grabaciones se presentan únicamente como material adicional y no sustituyen el video final de demostración.**


