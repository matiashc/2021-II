package co.edu.ufps.a2021_ii.red

import co.edu.ufps.a2021_ii.entity.Productos
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("db/")
    fun getProductos(): Call<Productos>

}