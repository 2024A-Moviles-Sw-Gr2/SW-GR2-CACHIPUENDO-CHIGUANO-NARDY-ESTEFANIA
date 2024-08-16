package com.example.sw2024agr2exnecc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class JugueteAdapter(private val context: Context, private val dataSource: List<Juguete>) : BaseAdapter() {

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
        val view: View = convertView ?: inflater.inflate(R.layout.item_juguete, parent, false)

        val nombreTextView = view.findViewById<TextView>(R.id.textViewNombreJuguete)
        val precioTextView = view.findViewById<TextView>(R.id.textViewPrecio)
        val edadRecomendadaTextView = view.findViewById<TextView>(R.id.textViewEdadRecomendada)
        val enStockTextView = view.findViewById<TextView>(R.id.textViewEnStock)

        val juguete = getItem(position) as Juguete

        nombreTextView.text = juguete.nombre
        precioTextView.text = "Precio: \$${juguete.precio}"
        edadRecomendadaTextView.text = "Edad Recomendada: ${juguete.edadRecomendada} años"
        enStockTextView.text = if (juguete.enStock) "En Stock" else "Agotado"

        return view
    }
}