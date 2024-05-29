import java.io.*

data class Juguete(
    var id: Int,
    var nombre: String,
    var precio: Double,
    var edadRecomendada: Int,
    var enStock: Boolean
) : Serializable {
    companion object {
        private const val FILE_NAME = "src/main/resources/juguetes.txt"

        fun guardarJuguetes(juguetes: List<Juguete>) {
            ObjectOutputStream(FileOutputStream(FILE_NAME)).use { it.writeObject(juguetes) }
        }

        fun cargarJuguetes(): MutableList<Juguete> {
            return if (File(FILE_NAME).exists()) {
                ObjectInputStream(FileInputStream(FILE_NAME)).use { it.readObject() as MutableList<Juguete> }
            } else {
                mutableListOf()
            }
        }

        fun crearJuguete(jugueteria: Jugueteria, juguete: Juguete) {
            jugueteria.juguetes.add(juguete)
            jugueteria.numeroTotalDeJuguetes++
            guardarJuguetes(jugueteria.juguetes)
            Jugueteria.guardarJugueterias(Jugueteria.cargarJugueterias())
        }

        fun actualizarJuguete(jugueteria: Jugueteria, id: Int, nuevoJuguete: Juguete) {
            val juguete = jugueteria.juguetes.find { it.id == id }
            if (juguete != null) {
                juguete.nombre = nuevoJuguete.nombre
                juguete.precio = nuevoJuguete.precio
                juguete.edadRecomendada = nuevoJuguete.edadRecomendada
                juguete.enStock = nuevoJuguete.enStock
                guardarJuguetes(jugueteria.juguetes)
                Jugueteria.guardarJugueterias(Jugueteria.cargarJugueterias())
            }
        }

        fun eliminarJuguete(jugueteria: Jugueteria, id: Int) {
            val jugueteEliminado = jugueteria.juguetes.removeIf { it.id == id }
            if (jugueteEliminado) {
                jugueteria.numeroTotalDeJuguetes = jugueteria.juguetes.size
                guardarJuguetes(cargarJuguetes().apply { removeIf { it.id == id } })
                val jugueterias = Jugueteria.cargarJugueterias()
                val index = jugueterias.indexOfFirst { it.id == jugueteria.id }
                if (index != -1) {
                    jugueterias[index] = jugueteria
                    Jugueteria.guardarJugueterias(jugueterias)
                }
            } else {
                println("El juguete con ID $id no se encontró en la juguetería ${jugueteria.nombre}.")
            }
        }

        fun leerJuguetes(juguetes: List<Juguete>) {
            juguetes.forEach { juguete ->
                println("\tJuguete ID: ${juguete.id}, Nombre: ${juguete.nombre}, Precio: ${juguete.precio}, Edad Recomendada: ${juguete.edadRecomendada}, En Stock: ${juguete.enStock}")
            }
        }
    }
}
