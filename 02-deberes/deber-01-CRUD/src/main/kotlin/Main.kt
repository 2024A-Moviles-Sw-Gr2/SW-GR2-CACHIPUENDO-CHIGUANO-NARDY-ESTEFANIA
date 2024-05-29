import java.time.LocalDate
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val jugueterias = Jugueteria.cargarJugueterias()

    while (true) {
        println("Menú de Opciones CRUD: Juguetería - Juguete")
        println("1. Crear Juguetería")
        println("2. Crear Juguete")
        println("3. Leer Jugueterías")
        println("4. Leer Juguetes de una Juguetería")
        println("5. Leer Todos los Juguetes")
        println("6. Actualizar Juguetería")
        println("7. Actualizar Juguete")
        println("8. Eliminar Juguetería")
        println("9. Eliminar Juguete")
        println("10. Finalizar Programa")
        print("Seleccione una opción: ")

        when (scanner.nextInt()) {
            1 -> {
                println("Ingrese el ID de la Juguetería:")
                val id = scanner.nextInt()
                scanner.nextLine() // Consume newline
                println("Ingrese el Nombre de la Juguetería:")
                val nombre = scanner.nextLine()
                println("Ingrese la Dirección de la Juguetería:")
                val direccion = scanner.nextLine()
                val fechaCreacion = LocalDate.now()
                val nuevaJugueteria = Jugueteria(id, nombre, direccion, fechaCreacion, 0, mutableListOf())
                Jugueteria.crearJugueteria(jugueterias, nuevaJugueteria)
            }
            2 -> {
                println("Ingrese el ID de la Juguetería a la que pertenece el Juguete:")
                val jugueteriaId = scanner.nextInt()
                val jugueteria = jugueterias.find { it.id == jugueteriaId }
                if (jugueteria != null) {
                    println("Ingrese el ID del Juguete:")
                    val id = scanner.nextInt()
                    scanner.nextLine() // Consume newline
                    println("Ingrese el Nombre del Juguete:")
                    val nombre = scanner.nextLine()
                    println("Ingrese el Precio del Juguete:")
                    val precio = scanner.nextDouble()
                    println("Ingrese la Edad Recomendada para el Juguete:")
                    val edadRecomendada = scanner.nextInt()
                    println("¿Está en stock el Juguete? (true/false):")
                    val enStock = scanner.nextBoolean()
                    val nuevoJuguete = Juguete(id, nombre, precio, edadRecomendada, enStock)
                    Juguete.crearJuguete(jugueteria, nuevoJuguete)
                    Jugueteria.guardarJugueterias(jugueterias) // Guardar cambios
                } else {
                    println("Juguetería no encontrada.")
                }
            }
            3 -> Jugueteria.leerJugueterias(jugueterias)
            4 -> {
                println("Ingrese el ID de la Juguetería cuyos juguetes desea ver:")
                val jugueteriaId = scanner.nextInt()
                val jugueteria = jugueterias.find { it.id == jugueteriaId }
                if (jugueteria != null) {
                    Juguete.leerJuguetes(jugueteria.juguetes)
                } else {
                    println("Juguetería no encontrada.")
                }
            }
            5 -> {
                val juguetes = Juguete.cargarJuguetes()
                Juguete.leerJuguetes(juguetes)
            }
            6 -> {
                println("Ingrese el ID de la Juguetería a actualizar:")
                val id = scanner.nextInt()
                scanner.nextLine() // Consume newline
                println("Ingrese el Nuevo Nombre de la Juguetería:")
                val nombre = scanner.nextLine()
                println("Ingrese la Nueva Dirección de la Juguetería:")
                val direccion = scanner.nextLine()
                val fechaCreacion = LocalDate.now()
                val nuevaJugueteria = Jugueteria(id, nombre, direccion, fechaCreacion, 0, mutableListOf())
                Jugueteria.actualizarJugueteria(jugueterias, id, nuevaJugueteria)
            }
            7 -> {
                println("Ingrese el ID de la Juguetería a la que pertenece el Juguete:")
                val jugueteriaId = scanner.nextInt()
                val jugueteria = jugueterias.find { it.id == jugueteriaId }
                if (jugueteria != null) {
                    println("Ingrese el ID del Juguete a actualizar:")
                    val id = scanner.nextInt()
                    scanner.nextLine() // Consume newline
                    println("Ingrese el Nuevo Nombre del Juguete:")
                    val nombre = scanner.nextLine()
                    println("Ingrese el Nuevo Precio del Juguete:")
                    val precio = scanner.nextDouble()
                    println("Ingrese la Nueva Edad Recomendada para el Juguete:")
                    val edadRecomendada = scanner.nextInt()
                    println("¿Está en stock el Juguete? (true/false):")
                    val enStock = scanner.nextBoolean()
                    val nuevoJuguete = Juguete(id, nombre, precio, edadRecomendada, enStock)
                    Juguete.actualizarJuguete(jugueteria, id, nuevoJuguete)
                    Jugueteria.guardarJugueterias(jugueterias) // Guardar cambios
                } else {
                    println("Juguetería no encontrada.")
                }
            }
            8 -> {
                println("Ingrese el ID de la Juguetería a eliminar:")
                val id = scanner.nextInt()
                Jugueteria.eliminarJugueteria(jugueterias, id)
            }
            9 -> {
                println("Ingrese el ID de la Juguetería a la que pertenece el Juguete:")
                val jugueteriaId = scanner.nextInt()
                val jugueteria = jugueterias.find { it.id == jugueteriaId }
                if (jugueteria != null) {
                    println("Ingrese el ID del Juguete a eliminar:")
                    val id = scanner.nextInt()
                    Juguete.eliminarJuguete(jugueteria, id)
                    Jugueteria.guardarJugueterias(jugueterias) // Guardar cambios
                } else {
                    println("Juguetería no encontrada.")
                }
            }
            10 -> {
                println("Finalizando el programa...")
                break
            }
            else -> println("Opción no válida. Intente de nuevo.")
        }
    }
}
