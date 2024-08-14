package com.example.a2024awgr2necc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class EditJugueteriaActivity : AppCompatActivity() {

    private lateinit var editTextToyStoreName: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextCreationDate: EditText
    private lateinit var editTextTotalToys: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private lateinit var dbHelper: ESqliteHelperJugueteria
    private var jugueteria: Jugueteria? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_jugueteria)

        dbHelper = ESqliteHelperJugueteria(this)
        editTextToyStoreName = findViewById(R.id.editTextToyStoreName)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextCreationDate = findViewById(R.id.editTextCreationDate)
        editTextTotalToys = findViewById(R.id.editTextTotalToys)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)

        jugueteria = intent.getParcelableExtra("JUGUETERIA")
        jugueteria?.let {
            editTextToyStoreName.setText(it.nombre)
            editTextAddress.setText(it.direccion)
            editTextCreationDate.setText(it.fechaCreacion.format(DateTimeFormatter.ISO_DATE))
            editTextTotalToys.setText(it.numeroTotalDeJuguetes.toString())
        }

        buttonSave.setOnClickListener {
            val name = editTextToyStoreName.text.toString()
            val address = editTextAddress.text.toString()
            val creationDate = LocalDate.parse(editTextCreationDate.text.toString(), DateTimeFormatter.ISO_DATE)
            val totalToys = editTextTotalToys.text.toString().toInt()

            if (jugueteria == null) {
                // Create new toy store
                dbHelper.crearJugueteria(name, address, creationDate, totalToys)
            } else {
                // Update existing toy store
                jugueteria?.apply {
                    nombre = name
                    direccion = address
                    fechaCreacion = creationDate
                    numeroTotalDeJuguetes = totalToys
                }
                dbHelper.actualizarJugueteria(jugueteria!!.id, name, address, creationDate, totalToys)
            }
            setResult(RESULT_OK)
            finish()
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }
}