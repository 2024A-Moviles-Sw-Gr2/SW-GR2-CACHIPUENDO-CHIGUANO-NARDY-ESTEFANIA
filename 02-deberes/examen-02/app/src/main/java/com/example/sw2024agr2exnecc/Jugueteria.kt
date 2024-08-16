package com.example.sw2024agr2exnecc

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate


class Jugueteria(
    var id: Int,
    var nombre: String,
    var direccion: String,
    var fechaCreacion: LocalDate,
    var numeroTotalDeJuguetes: Int,
    var juguetes: MutableList<Juguete>,
    var latitude: Double, // Add latitude
    var longitude: Double // Add longitude
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        LocalDate.parse(parcel.readString()!!),
        parcel.readInt(),
        mutableListOf<Juguete>().apply {
            parcel.readList(this as List<*>, Juguete::class.java.classLoader)
        },
        parcel.readDouble(), // Read latitude
        parcel.readDouble()  // Read longitude
    )

    override fun toString(): String {
        return "$nombre $direccion"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(direccion)
        parcel.writeString(fechaCreacion.toString())
        parcel.writeInt(numeroTotalDeJuguetes)
        parcel.writeList(juguetes as List<*>)
        parcel.writeDouble(latitude) // Write latitude
        parcel.writeDouble(longitude) // Write longitude
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jugueteria> {
        override fun createFromParcel(parcel: Parcel): Jugueteria {
            return Jugueteria(parcel)
        }

        override fun newArray(size: Int): Array<Jugueteria?> {
            return arrayOfNulls(size)
        }
    }
}
