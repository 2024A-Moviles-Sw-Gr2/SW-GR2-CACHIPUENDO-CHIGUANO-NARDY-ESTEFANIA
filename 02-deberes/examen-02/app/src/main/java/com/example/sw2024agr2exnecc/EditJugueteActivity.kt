package com.example.sw2024agr2exnecc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText


class EditJugueteActivity : AppCompatActivity() {

    private lateinit var editTextToyName: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextAgeRecommended: EditText
    private lateinit var editTextInStock: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private lateinit var dbHelper: ESqliteHelperJuguete
    private var juguete: Juguete? = null
    private var jugueteriaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_juguete)

        dbHelper = ESqliteHelperJuguete(this)
        editTextToyName = findViewById(R.id.editTextToyName)
        editTextPrice = findViewById(R.id.editTextPrice)
        editTextAgeRecommended = findViewById(R.id.editTextAgeRecommended)
        editTextInStock = findViewById(R.id.editTextInStock)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)

        jugueteriaId = intent.getIntExtra("JUGUETERIA_ID", -1)
        juguete = intent.getParcelableExtra("JUGUETE")
        juguete?.let {
            editTextToyName.setText(it.nombre)
            editTextPrice.setText(it.precio.toString())
            editTextAgeRecommended.setText(it.edadRecomendada.toString())
            editTextInStock.setText(if (it.enStock) "Yes" else "No")
        }

        buttonSave.setOnClickListener {
            val name = editTextToyName.text.toString()
            val price = editTextPrice.text.toString().toDouble()
            val ageRecommended = editTextAgeRecommended.text.toString().toInt()
            val inStock = editTextInStock.text.toString().equals("Yes", ignoreCase = true)

            if (juguete == null) {
                // Create new toy
                dbHelper.crearJuguete(name, price, ageRecommended, inStock, jugueteriaId)
            } else {
                // Update existing toy
                juguete?.apply {
                    nombre = name
                    precio = price
                    edadRecomendada = ageRecommended
                    enStock = inStock
                }
                dbHelper.actualizarJuguete(juguete!!.id, name, price, ageRecommended, inStock)
            }
            finish()
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }
}