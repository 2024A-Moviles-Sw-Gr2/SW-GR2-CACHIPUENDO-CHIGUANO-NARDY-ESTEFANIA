package com.example.a2024awgr2necc

import android.os.Parcel
import android.os.Parcelable

class Juguete(
    var id: Int,
    var nombre: String,
    var precio: Double,
    var edadRecomendada: Int,
    var enStock: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    )

    override fun toString(): String {
        return "$nombre $precio"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeDouble(precio)
        parcel.writeInt(edadRecomendada)
        parcel.writeByte(if (enStock) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Juguete> {
        override fun createFromParcel(parcel: Parcel): Juguete {
            return Juguete(parcel)
        }

        override fun newArray(size: Int): Array<Juguete?> {
            return arrayOfNulls(size)
        }
    }
}