package co.edu.ufps.a2021_ii.vistas

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.edu.ufps.a2021_ii.R
import co.edu.ufps.a2021_ii.bd.AdminSQLiteOpenHelper
import co.edu.ufps.a2021_ii.entity.Libro

class OperacionesBaseDatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operaciones_base_datos)
        val id = findViewById<EditText>(R.id.id)
        val titulo = findViewById<EditText>(R.id.titulo)
        val descripcion = findViewById<EditText>(R.id.descripcion)
        val isbn = findViewById<EditText>(R.id.isbn)
        val imagen = findViewById<EditText>(R.id.imagen)
        val guardar = findViewById<Button>(R.id.guardar)
        val consultar =  findViewById<Button>(R.id.consultar)
        val eliminar = findViewById<Button>(R.id.eliminar)
        val modificar = findViewById<Button>(R.id.modificar)
        guardar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"app",null,1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            val libro = Libro(
                id.text.toString(),
                titulo.text.toString(),
                descripcion.text.toString(),
                isbn.text.toString(),
                imagen.text.toString()
            )
            registro.put("id",libro.id)
            registro.put("titulo",libro.titulo)
            registro.put("descripcion",libro.descripcion)
            registro.put("isbn", libro.isbn)
            registro.put("imagen", libro.imagen)
            bd.insert("libros",null,registro)
            bd.close()
        }

        consultar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"app",null,1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("SELECT titulo, descripcion, isbn, imagen FROM libros WHERE id=${id.text.toString()}",null)
            if(fila.moveToFirst()){
                id.setText(fila.getString(0))
                titulo.setText(fila.getString(1))
                descripcion.setText(fila.getString(2))
                isbn.setText(fila.getString(3))
                imagen.setText(fila.getString(4))
            }
            bd.close()
        }

        eliminar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"app",null,1)
            val bd = admin.writableDatabase
            val cant = bd.delete("libros","id=${id.text.toString()}", null)
            bd.close()
            if(cant == 1){
                Toast.makeText(this,"se borro con exito",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"No se borro",Toast.LENGTH_SHORT).show()
        }

        modificar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"app",null,1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            val libro = Libro(
                id.text.toString(),
                titulo.text.toString(),
                descripcion.text.toString(),
                isbn.text.toString(),
                imagen.text.toString()
            )
            registro.put("titulo",libro.titulo)
            registro.put("descripcion",libro.descripcion)
            registro.put("isbn", libro.isbn)
            registro.put("imagen", libro.imagen)
            val cant = bd.update("libros", registro,"id=${libro.id}",null)
            bd.close()
            if (cant == 1){
                Toast.makeText(this,"se actualizo con exito",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"No actualizo",Toast.LENGTH_SHORT).show()

        }



    }
}