package br.com.fiap.datapersistence

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var btnSave : Button
    lateinit var txtName : TextView
    lateinit var treatmentList : Spinner
    lateinit var btnView : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave = findViewById(R.id.btnSave)
        txtName = findViewById(R.id.txtName)
        treatmentList = findViewById(R.id.treatmentList)
        btnView = findViewById(R.id.btnView)

        var db = DatabaseManager(this, "greetings")

        btnSave.setOnClickListener(View.OnClickListener {

            // SHARED PREFERENCES
            /*var saudacaoPersistencia = this.getSharedPreferences("greeting", Context.MODE_PRIVATE)
            var editor = saudacaoPersistencia.edit()
            editor.putString("name",txtName.text.toString())
            editor.putString("treatment",treatmentList.selectedItem.toString())
            editor.apply()*/

            // FILE
            /*var data = txtName.text.toString() + ":" + treatmentList.selectedItem.toString()
            writeDataFile("greeting",data)*/

            // SQLITE
            db.removeGreeting()
            db.insertGreeting(1, txtName.text.toString(), treatmentList.selectedItem.toString())

            Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
        })
        btnView.setOnClickListener(View.OnClickListener {
            var intent = Intent(this,GreetingActivity::class.java)
            startActivity(intent)
        })
    }

    /**
     * FILE
     */
    fun writeDataFile(filename: String, data: String) {

        try {
            var fs = openFileOutput(filename,Context.MODE_PRIVATE);

            fs.write(data.toByteArray())
            fs.close()
        }
        catch (e: FileNotFoundException){
            Log.i("writeDataFile","FileNotFoundException")
        }
        catch (e: IOException){
            Log.i("writeDataFile","IOException")
        }
    }
}