package co.edu.ufps.a2021_ii.entity

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class Productos {
    @SerializedName("success")
    private var success: Boolean? = null
    @SerializedName("productos")
    private lateinit var productos: ArrayList<Producto>

    fun Productos( success: Boolean?, productos: ArrayList<Producto>){
        this.success= success
        this.productos= productos
    }


}