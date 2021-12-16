package co.edu.ufps.a2021_ii.vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import co.edu.ufps.a2021_ii.R
import co.edu.ufps.a2021_ii.entity.Libro
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class AgregarLibro : AppCompatActivity() {
    lateinit var titulo: TextInputEditText
    lateinit var descripcion: TextInputEditText
    lateinit var isbn: TextInputEditText
    lateinit var imagen: TextInputEditText
    lateinit var guardar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_libro)
        titulo = findViewById(R.id.titulo)
        descripcion = findViewById(R.id.descripcion)
        isbn = findViewById(R.id.isbn)
        imagen = findViewById(R.id.imagen)
        guardar = findViewById(R.id.guardar)
        guardar.setOnClickListener{
            registrarLibro()
        }

    }

    private fun registrarLibro() {
        val dataBase = FirebaseDatabase.getInstance()
        val myRef = dataBase.reference
        val libro = Libro(
            myRef.push().key.toString(),
            titulo.text.toString(),
            descripcion.text.toString(),
            isbn.text.toString(),
            imagen.text.toString()

        )
        myRef.child("libros").child(libro.id).setValue(libro)
        finish()
    }
}