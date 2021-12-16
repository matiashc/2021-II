package co.edu.ufps.a2021_ii.vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import co.edu.ufps.a2021_ii.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class SubirImagenActivity : AppCompatActivity() {
    lateinit var uploadImageView: ImageView
    private val file=1
    private val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("archivo")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_imagen)
        uploadImageView = findViewById(R.id.imageView)
        uploadImageView.setOnClickListener{
            fileUpload()
        }
    }

    private fun fileUpload() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent,file)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == file){
            if (resultCode == RESULT_OK){
                val fileUri = data!!.data
                val folder: StorageReference =
                    FirebaseStorage.getInstance().getReference().child("archivo")
                val file_name: StorageReference = folder.child("file" + fileUri!!.lastPathSegment)
                file_name.putFile(fileUri).addOnSuccessListener {
                    file_name.downloadUrl.addOnSuccessListener {
                        val hashMap =
                            HashMap<String, String>()
                        hashMap["link"]= java.lang.String.valueOf(it)
                        myRef.setValue(hashMap)
                        Log.d("Mensaje","Se subio Correctamente")
                    }
                }

            }
        }

    }
}