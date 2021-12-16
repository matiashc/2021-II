package co.edu.ufps.a2021_ii.vistas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import co.edu.ufps.a2021_ii.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MultipleArchivosActivity : AppCompatActivity() {
    lateinit var uploadImageView: ImageView
    private val file=1
    private val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("archivo")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_archivos)
        uploadImageView = findViewById(R.id.imageView)
        uploadImageView.setOnClickListener{
            fileUploads()
        }
    }

    private fun fileUploads() {
        val intent = Intent (Intent.ACTION_GET_CONTENT)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2){
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        }
        intent.type = "*/*"
        startActivityForResult(intent,file)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == file){
            if (resultCode == RESULT_OK && data!= null){
                val clipData = data.clipData
                if (clipData != null){
                    for(i in 0 until clipData.itemCount){
                        val uri = clipData.getItemAt(i).uri
                        uri?.let { fileUpload(it) }
                        }
                }
                else {
                    val uri = data.data
                    uri?.let { fileUpload(it) }
                }
            }
        }
    }

    private fun fileUpload(myUri: Uri?) {
        val folder: StorageReference =
            FirebaseStorage.getInstance().getReference().child("archivo")
        val paht =  myUri!!.lastPathSegment.toString()
        val fileName: StorageReference = folder.child(paht.substring(paht.lastIndexOf('/')+ 1))
        fileName.putFile(myUri).addOnSuccessListener {
            fileName.downloadUrl.addOnSuccessListener {
                val hashMap = HashMap<String, String>()
                hashMap["link"]=java.lang.String.valueOf(it)
                myRef.child(myRef.push().key.toString()).setValue(hashMap)
                Log.i("guardar Archivos","exitoso")

            }
        }.addOnFailureListener{
            Log.i("subir archivos", "Fallo")
        }

    }
}
