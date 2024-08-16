package com.example.sw2024agr2exnecc

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
    private lateinit var editTextLatitude: EditText // Add for latitude
    private lateinit var editTextLongitude: EditText // Add for longitude
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
        editTextLatitude = findViewById(R.id.editTextLatitude) // Add for latitude
        editTextLongitude = findViewById(R.id.editTextLongitude) // Add for longitude

        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)

        jugueteria = intent.getParcelableExtra("JUGUETERIA")
        jugueteria?.let {
            editTextToyStoreName.setText(it.nombre)
            editTextAddress.setText(it.direccion)
            editTextCreationDate.setText(it.fechaCreacion.format(DateTimeFormatter.ISO_DATE))
            editTextTotalToys.setText(it.numeroTotalDeJuguetes.toString())
            editTextLatitude.setText(it.latitude.toString()) // Add for latitude
            editTextLongitude.setText(it.longitude.toString()) // Add for longitude

        }

        buttonSave.setOnClickListener {
            val name = editTextToyStoreName.text.toString()
            val address = editTextAddress.text.toString()
            val creationDate = LocalDate.parse(editTextCreationDate.text.toString(), DateTimeFormatter.ISO_DATE)
            val totalToys = editTextTotalToys.text.toString().toInt()
            val latitude = editTextLatitude.text.toString().toDouble() // Add for latitude
            val longitude = editTextLongitude.text.toString().toDouble() // Add for longitude

            if (jugueteria == null) {
                dbHelper.crearJugueteria(name, address, creationDate, totalToys, latitude, longitude)
            } else {
                jugueteria?.apply {
                    nombre = name
                    direccion = address
                    fechaCreacion = creationDate
                    numeroTotalDeJuguetes = totalToys
                    this.latitude = latitude
                    this.longitude = longitude
                }
                dbHelper.actualizarJugueteria(jugueteria!!.id, name, address, creationDate, totalToys, latitude, longitude)
            }
            setResult(RESULT_OK)
            finish()
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }
}