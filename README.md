
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
