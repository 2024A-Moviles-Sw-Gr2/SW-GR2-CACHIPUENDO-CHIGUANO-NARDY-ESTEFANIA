### Descripción Breve del Proyecto

#### Objetivo
El objetivo de este proyecto es implementar un sistema CRUD (Crear, Leer, Actualizar, Eliminar) en Kotlin para gestionar una juguetería y sus juguetes, asegurando la persistencia de los datos en archivos.

#### Estructura del Proyecto

1. **Clases Principales:**
    - **Juguetería (`Jugueteria.kt`)**: Representa una juguetería que contiene varios juguetes. Incluye atributos como `id`, `nombre`, `direccion`, `fechaCreacion`, `numeroTotalDeJuguetes` y una lista de `juguetes`.
    - **Juguete (`Juguete.kt`)**: Representa un juguete con atributos como `id`, `nombre`, `precio`, `edadRecomendada` y `enStock`.

2. **Persistencia de Datos:**
    - La persistencia se realiza mediante la serialización de objetos, utilizando `ObjectOutputStream` para guardar y `ObjectInputStream` para cargar datos de archivos binarios (`jugueterias.dat` y `juguetes.dat`).

3. **Operaciones CRUD:**
    - **Crear**: Se pueden crear nuevas jugueterías y juguetes, que se almacenan en los respectivos archivos.
    - **Leer**: Se pueden leer y mostrar las jugueterías y juguetes almacenados.
    - **Actualizar**: Se pueden actualizar los detalles de las jugueterías y juguetes existentes.
    - **Eliminar**: Se pueden eliminar jugueterías y juguetes del sistema.

4. **Interfaz de Usuario en Consola (`Main.kt`):**
    - El programa ofrece un menú interactivo para que los usuarios realicen las operaciones CRUD.
    - El menú incluye opciones para crear, leer, actualizar y eliminar tanto jugueterías como juguetes, y finaliza la ejecución del programa cuando se selecciona la opción correspondiente.

#### Menú de Opciones
El menú interactivo ofrece las siguientes opciones:
1. Crear Juguetería
2. Crear Juguete
3. Leer Jugueterías
4. Leer Juguetes de una Juguetería
5. Leer Todos los Juguetes
6. Actualizar Juguetería
7. Actualizar Juguete
8. Eliminar Juguetería
9. Eliminar Juguete
10. Finalizar Programa

#### Persistencia en Archivos
La persistencia en archivos se maneja de la siguiente manera:
- **Guardar Jugueterías y Juguetes:**
    - Serializamos los objetos usando `ObjectOutputStream` y los escribimos en archivos (`jugueterias.dat` y `juguetes.dat`).
    - Ejemplo:
      ```kotlin
      ObjectOutputStream(FileOutputStream("jugueterias.txt")).use { it.writeObject(jugueterias) }
      ```
- **Cargar Jugueterías y Juguetes:**
    - Deserializamos los objetos usando `ObjectInputStream` y los leemos de los archivos.
    - Ejemplo:
      ```kotlin
      ObjectInputStream(FileInputStream("jugueterias.txt")).use { it.readObject() as MutableList<Jugueteria> }
      ```

Este enfoque asegura que los datos de las jugueterías y juguetes se conserven entre ejecuciones del programa, permitiendo un manejo efectivo y persistente de la información.