package co.edu.ufps.a2021_ii.entity

import com.google.gson.annotations.SerializedName

data class Producto(@SerializedName("id") var id: String,
                    @SerializedName("nombre") var nombre: String,
                    @SerializedName("descripcion") var descripcion: String,
                    @SerializedName("photo") var photo: String,
                    @SerializedName("precio") var precio: Int,
                    @SerializedName("cantidad")var cantidad: Int)
