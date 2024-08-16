package com.example.sw2024agr2exnecc

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperJuguete(contexto: Context?) :
    SQLiteOpenHelper(contexto, "toyDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaJuguete = """
            CREATE TABLE JUGUETE (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                precio REAL,
                edadRecomendada INTEGER,
                enStock INTEGER,
                toyStoreId INTEGER
            )
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaJuguete)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle database upgrade as needed
    }

    fun crearJuguete(nombre: String, precio: Double, edadRecomendada: Int, enStock: Boolean, toyStoreId: Int): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("edadRecomendada", edadRecomendada)
        valoresAGuardar.put("enStock", if (enStock) 1 else 0)
        valoresAGuardar.put("toyStoreId", toyStoreId)
        val resultadoGuardar = basedatosEscritura.insert("JUGUETE", null, valoresAGuardar)
        basedatosEscritura.close()
        return resultadoGuardar != -1L
    }

    fun eliminarJuguete(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete("JUGUETE", "id=?", parametrosConsultaDelete)
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun actualizarJuguete(id: Int, nombre: String, precio: Double, edadRecomendada: Int, enStock: Boolean): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("precio", precio)
        valoresAActualizar.put("edadRecomendada", edadRecomendada)
        valoresAActualizar.put("enStock", if (enStock) 1 else 0)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura.update("JUGUETE", valoresAActualizar, "id=?", parametrosConsultaActualizar)
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarJuguetePorID(id: Int): Juguete? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM JUGUETE WHERE id = ?"
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadosConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsultaLectura)
        val existeAlMenosUno = resultadosConsultaLectura.moveToFirst()
        val juguete = if (existeAlMenosUno) {
            Juguete(
                id = resultadosConsultaLectura.getInt(0),
                nombre = resultadosConsultaLectura.getString(1),
                precio = resultadosConsultaLectura.getDouble(2),
                edadRecomendada = resultadosConsultaLectura.getInt(3),
                enStock = resultadosConsultaLectura.getInt(4) == 1
            )
        } else {
            null
        }
        resultadosConsultaLectura.close()
        baseDatosLectura.close()
        return juguete
    }

    fun consultarJuguetesPorTienda(toyStoreId: Int): List<Juguete> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM JUGUETE WHERE toyStoreId = ?"
        val parametrosConsultaLectura = arrayOf(toyStoreId.toString())
        val resultadosConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsultaLectura)
        val juguetes = mutableListOf<Juguete>()
        if (resultadosConsultaLectura.moveToFirst()) {
            do {
                val juguete = Juguete(
                    id = resultadosConsultaLectura.getInt(0),
                    nombre = resultadosConsultaLectura.getString(1),
                    precio = resultadosConsultaLectura.getDouble(2),
                    edadRecomendada = resultadosConsultaLectura.getInt(3),
                    enStock = resultadosConsultaLectura.getInt(4) == 1
                )
                juguetes.add(juguete)
            } while (resultadosConsultaLectura.moveToNext())
        }
        resultadosConsultaLectura.close()
        baseDatosLectura.close()
        return juguetes
    }
}