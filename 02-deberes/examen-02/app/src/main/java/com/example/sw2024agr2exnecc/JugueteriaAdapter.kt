package com.example.sw2024agr2exnecc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class JugueteriaAdapter(private val context: Context, private val dataSource: List<Jugueteria>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.item_jugueteria, parent, false)

        val nombreTextView = view.findViewById<TextView>(R.id.textViewNombre)
        val direccionTextView = view.findViewById<TextView>(R.id.textViewDireccion)
        val fechaCreacionTextView = view.findViewById<TextView>(R.id.textViewFechaCreacion)
        val totalJuguetesTextView = view.findViewById<TextView>(R.id.textViewTotalJuguetes)
        val latitudeTextView = view.findViewById<TextView>(R.id.textViewLatitude) // New TextView for latitude
        val longitudeTextView = view.findViewById<TextView>(R.id.textViewLongitude) // New TextView for longitude

        val jugueteria = getItem(position) as Jugueteria

        nombreTextView.text = jugueteria.nombre
        direccionTextView.text = "Dirección: ${jugueteria.direccion}"
        fechaCreacionTextView.text = "Creada: ${jugueteria.fechaCreacion}"
        totalJuguetesTextView.text = "Total Juguetes: ${jugueteria.numeroTotalDeJuguetes}"
        latitudeTextView.text = "Latitud: ${jugueteria.latitude}" // Bind latitude data
        longitudeTextView.text = "Longitud: ${jugueteria.longitude}" // Bind longitude data

        return view
    }
}
