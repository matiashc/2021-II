package co.edu.ufps.a2021_ii.controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.ufps.a2021_ii.R
import co.edu.ufps.a2021_ii.entity.Producto

class AdapterProducto(dataSet: ArrayList<Producto>):
    RecyclerView.Adapter<AdapterProducto.ProductoViewHolder>() {
    private val dataSet: ArrayList<Producto> = TODO()

    class ProductoViewHolder (view: View):
        RecyclerView.ViewHolder(view){
            var productoNombre: TextView
            var productoDescripcion: TextView
            var productoPrecio: TextView
            var productoCantidad: TextView
            init {
                productoNombre = view.findViewById(R.id.producto_nombre)
                productoDescripcion = view.findViewById(R.id.producto_descripcion)
                productoPrecio = view.findViewById(R.id.producto_precio)
                productoCantidad = view.findViewById(R.id.producto_cantidad)
            }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutinflater= LayoutInflater.from(parent.context)
        val view: View = layoutinflater.inflate(R.layout.card_producto,parent,false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.productoNombre.setText(dataSet[position].nombre)
        holder.productoDescripcion.setText(dataSet[position].descripcion)
        holder.productoCantidad.setText(dataSet[position].cantidad)
        holder.productoPrecio.setText(dataSet[position].precio)

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}