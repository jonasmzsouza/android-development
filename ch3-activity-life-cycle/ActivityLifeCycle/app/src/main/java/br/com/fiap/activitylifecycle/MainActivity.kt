package br.com.fiap.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var edt_name : EditText
    lateinit var txv_result : TextView
    lateinit var btn_clickHere : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_name = findViewById(R.id.edt_name)
        txv_result = findViewById(R.id.txv_result)
        btn_clickHere = findViewById(R.id.btn_clickHere)

        // listener onClick
        btn_clickHere.setOnClickListener {

            // Retrieving the text typed by the user and assigning it to a String
            var nome: String = edt_name.editableText.toString()

            // Changing the text inside the TextView
            txv_result.text = nome

            Toast.makeText(this,"Testing",Toast.LENGTH_SHORT).show()

            val intent = Intent(this, NextActivity::class.java)
            startActivity(intent)

        }
        Log.i("onCreate", "OnCreate Activated")
    }
    //-----------------------------------------------------------
    override fun onStart() {
        super.onStart()

        Log.i("onStart", "onStart Activated")
    }
    //-----------------------------------------------------------
    override fun onResume() {
        super.onResume()

        Log.i("onResume", "onResume Activated")
    }
    //-----------------------------------------------------------
    override fun onPause() {
        super.onPause()

        Log.i("onPause", "onPause Activated")
    }
    //-----------------------------------------------------------
    override fun onStop() {
        super.onStop()

        Log.i("onStop", "onStop Activated")
    }
    //-----------------------------------------------------------
    override fun onDestroy() {
        super.onDestroy()

        Log.i("onDestroy", "onDestroy Activated")
    }
    //-----------------------------------------------------------
    override fun onRestart() {
        super.onRestart()

        Log.i("onRestart", "onRestart Activated")
    }

}