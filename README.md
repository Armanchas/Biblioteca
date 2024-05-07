## Programa de administracion de biblioteca para analizar con sonarqube

El programa cumple la funcionalidad administrativa de agregar, modificar o eliminar libros de la biblioteca, y crear, modificar o eliminar usuarios de dicha biblioteca

Los usuarios solamente pueden ser creados desde el panel del administrador, en donde se les da su nombre de usuario, contraseña y tipo de usuario. El ID se le asigna automaticamente en la base de datos.
Estos usuarios pueden despues pedir libros prestados y luego devolverlos.


### Como ejecutar

Preferiblemente clonando el proyecto en el directorio que prefiera, abrendolo con IntelliJ, usando java 17 y ejecutando la clase Main. Eso si, antes de eso hay que crear la base de datos a la que se va a conectar el programa.

El programa usa como motor de base de datos PostgreSQL, entonces va a ser necesario crear un servidor local postgres con una base de datos nueva a la que el programa se pueda conectar. Una vez hecho eso hay que editar la clase DatabaseConnection.java, en la que se encuentra el metodo para conectarse a la base de datos. Ahi hay que modificar las credenciales a las que correspondan a la base de datos que haya creado.

Tambien hay un script en ./db/create/create_tables.sql que hay que ejecutar para crear las tablas necesarias y para insertar algunos datos necesarios para la demostracion.

Una vez hecho todo eso el programa esta listo para ejecutarse desde la clase Main. Si hay algun problema o me ha faltado algo, por favor contactarme.

