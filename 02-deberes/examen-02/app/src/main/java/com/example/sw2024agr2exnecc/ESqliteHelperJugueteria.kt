package com.example.sw2024agr2exnecc

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ESqliteHelperJugueteria(contexto: Context?) :
    SQLiteOpenHelper(contexto, "toyStoreDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaJugueteria = """
            CREATE TABLE JUGUETERIA (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                direccion VARCHAR(50),
                fechaCreacion VARCHAR(10),
                numeroTotalDeJuguetes INTEGER,
                latitude DOUBLE,  -- Add latitude
                longitude DOUBLE  -- Add longitude
            )
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaJugueteria)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle database upgrade as needed
    }

    fun crearJugueteria(nombre: String, direccion: String, fechaCreacion: LocalDate, numeroTotalDeJuguetes: Int, latitude: Double, longitude: Double): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues().apply {
            put("nombre", nombre)
            put("direccion", direccion)
            put("fechaCreacion", fechaCreacion.format(DateTimeFormatter.ISO_DATE))
            put("numeroTotalDeJuguetes", numeroTotalDeJuguetes)
            put("latitude", latitude)  // Add latitude
            put("longitude", longitude)  // Add longitude
        }
        val resultadoGuardar = basedatosEscritura.insert("JUGUETERIA", null, valoresAGuardar)
        basedatosEscritura.close()
        return resultadoGuardar != -1L
    }

    fun eliminarJugueteria(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete("JUGUETERIA", "id=?", parametrosConsultaDelete)
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun actualizarJugueteria(id: Int, nombre: String, direccion: String, fechaCreacion: LocalDate, numeroTotalDeJuguetes: Int, latitude: Double, longitude: Double): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues().apply {
            put("nombre", nombre)
            put("direccion", direccion)
            put("fechaCreacion", fechaCreacion.format(DateTimeFormatter.ISO_DATE))
            put("numeroTotalDeJuguetes", numeroTotalDeJuguetes)
            put("latitude", latitude)  // Add latitude
            put("longitude", longitude)  // Add longitude
        }
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update("JUGUETERIA", valoresAActualizar, "id=?", parametrosConsultaActualizar)
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarJugueteriaPorID(id: Int): Jugueteria? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM JUGUETERIA WHERE id = ?"
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadosConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsultaLectura)
        val existeAlMenosUno = resultadosConsultaLectura.moveToFirst()
        val jugueteria = if (existeAlMenosUno) {
            Jugueteria(
                id = resultadosConsultaLectura.getInt(0),
                nombre = resultadosConsultaLectura.getString(1),
                direccion = resultadosConsultaLectura.getString(2),
                fechaCreacion = LocalDate.parse(resultadosConsultaLectura.getString(3)),
                numeroTotalDeJuguetes = resultadosConsultaLectura.getInt(4),
                juguetes = mutableListOf(), // This should be populated from the toys table
                latitude = resultadosConsultaLectura.getDouble(5), // Add latitude
                longitude = resultadosConsultaLectura.getDouble(6) // Add longitude
            )
        } else {
            null
        }
        resultadosConsultaLectura.close()
        baseDatosLectura.close()
        return jugueteria
    }


    fun getAllToyStores(): List<Jugueteria> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM JUGUETERIA"
        val resultadosConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        val listaJugueterias = mutableListOf<Jugueteria>()
        if (resultadosConsultaLectura.moveToFirst()) {
            do {
                val jugueteria = Jugueteria(
                    id = resultadosConsultaLectura.getInt(0),
                    nombre = resultadosConsultaLectura.getString(1),
                    direccion = resultadosConsultaLectura.getString(2),
                    fechaCreacion = LocalDate.parse(resultadosConsultaLectura.getString(3)),
                    numeroTotalDeJuguetes = resultadosConsultaLectura.getInt(4),
                    latitude = resultadosConsultaLectura.getDouble(5), // Reading latitude
                    longitude = resultadosConsultaLectura.getDouble(6), // Reading longitude
                    juguetes = mutableListOf() // Populate this with actual data as needed
                )
                listaJugueterias.add(jugueteria)
            } while (resultadosConsultaLectura.moveToNext())
        }
        resultadosConsultaLectura.close()
        baseDatosLectura.close()
        return listaJugueterias
    }


}