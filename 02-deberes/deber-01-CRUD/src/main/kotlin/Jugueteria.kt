import java.io.*
import java.time.LocalDate

data class Jugueteria(
    var id: Int,
    var nombre: String,
    var direccion: String,
    var fechaCreacion: LocalDate,
    var numeroTotalDeJuguetes: Int,
    var juguetes: MutableList<Juguete>
) : Serializable {
    companion object {
        private const val FILE_NAME = "src/main/resources/jugueterias.txt"

        fun guardarJugueterias(jugueterias: List<Jugueteria>) {
            ObjectOutputStream(FileOutputStream(FILE_NAME)).use { it.writeObject(jugueterias) }
        }

        fun cargarJugueterias(): MutableList<Jugueteria> {
            return if (File(FILE_NAME).exists()) {
                ObjectInputStream(FileInputStream(FILE_NAME)).use { it.readObject() as MutableList<Jugueteria> }
            } else {
                mutableListOf()
            }
        }

        fun crearJugueteria(jugueterias: MutableList<Jugueteria>, jugueteria: Jugueteria) {
            jugueterias.add(jugueteria)
            guardarJugueterias(jugueterias)
        }

        fun actualizarJugueteria(jugueterias: MutableList<Jugueteria>, id: Int, nuevaJugueteria: Jugueteria) {
            val jugueteria = jugueterias.find { it.id == id }
            if (jugueteria != null) {
                jugueteria.nombre = nuevaJugueteria.nombre
                jugueteria.direccion = nuevaJugueteria.direccion
                jugueteria.fechaCreacion = nuevaJugueteria.fechaCreacion
                jugueteria.numeroTotalDeJuguetes = nuevaJugueteria.numeroTotalDeJuguetes
                guardarJugueterias(jugueterias)
            }
        }

        fun eliminarJugueteria(jugueterias: MutableList<Jugueteria>, id: Int) {
            jugueterias.removeIf { it.id == id }
            guardarJugueterias(jugueterias)
        }

        fun leerJugueterias(jugueterias: List<Jugueteria>) {
            jugueterias.forEach { jugueteria ->
                println("Juguetería ID: ${jugueteria.id}, Nombre: ${jugueteria.nombre}, Dirección: ${jugueteria.direccion}, Fecha de Creación: ${jugueteria.fechaCreacion}, Número Total de Juguetes: ${jugueteria.numeroTotalDeJuguetes}")
                Juguete.leerJuguetes(jugueteria.juguetes)
            }
        }
    }
}
