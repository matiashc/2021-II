package co.edu.ufps.a2021_ii.fragmentos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.ufps.a2021_ii.R
import co.edu.ufps.a2021_ii.controlador.AdarterLibro
import co.edu.ufps.a2021_ii.entity.Libro
import co.edu.ufps.a2021_ii.vistas.AgregarLibro
import co.edu.ufps.a2021_ii.vistas.ContenedorActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibroFragment : Fragment() {
    lateinit var contenedorLibros:RecyclerView
    lateinit var adapterLibro: AdarterLibro
    lateinit var registrarLibro: FloatingActionButton

    private var param1:String? =null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_libro, container, false)
        contenedorLibros = view.findViewById(R.id.contenedor_libro)
        val linearLayout =LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        contenedorLibros.layoutManager=linearLayout
        adapterLibro = AdarterLibro(context,cargarDatosFireBase(),R.id.card)
        contenedorLibros.adapter=adapterLibro
        registrarLibro = view.findViewById(R.id.registrar_libro)
        registrarLibro.setOnClickListener(View.OnClickListener { agregarLibro() })
        return view

    }

    private fun agregarLibro() {
        val intent = Intent(context,AgregarLibro::class.java)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LibroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun cargarDatos(): ArrayList<Libro>{
        val libros: ArrayList <Libro>  = java.util.ArrayList()
        libros.add (
            Libro ("13a",
                "JAVA",
                "JAVA FUNDAMENTOS",
                "RTGSR",
                "https://image.isu.pub/140916154841-15f074f87b0c1f94c6b58f43bc2036e7/jpg/page_1.jpg"))
        libros.add (Libro ("13b",
            "PYTHON",
            "PYTHON FUNDAMENTOS",
            "RTGSR",
            "https://image.isu.pub/140916154841-15f074f87b0c1f94c6b58f43bc2036e7/jpg/page_1.jpg"))
        return libros
    }

    fun cargarDatosFireBase(): ArrayList<Libro>{
        val libros= ArrayList<Libro>()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance();
        val myRef: DatabaseReference = database.reference
        // Read from the database
        myRef.child("libros").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (snapshot.exists()){
                    libros.clear()
                    for(data in snapshot.children){
                        val libro=data.getValue(Libro::class.java)
                        libros.add(libro as Libro)
                        adapterLibro.notifyDataSetChanged()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("LibrosFragment", "Failed to read value.", error.toException())
            }

        })
        return libros;
    }
}