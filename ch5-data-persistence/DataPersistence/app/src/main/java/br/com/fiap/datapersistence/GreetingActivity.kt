package br.com.fiap.datapersistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class GreetingActivity : AppCompatActivity() {

    lateinit var lbGreeting : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        lbGreeting = findViewById(R.id.lbGreeting)

        var db = DatabaseManager(this, "greetings")

        // SHARED PREFERENCES
        /*var greetingPersistence = this.getSharedPreferences("greeting", Context.MODE_PRIVATE)
        var name = greetingPersistence.getString("name","")
        var treatment = greetingPersistence.getString("treatment","")*/

        // FILE
        /*var data = recoverFileData("greeting")
        var tokenizer = StringTokenizer(data,":")
        var name = tokenizer.nextToken()
        var treatment = tokenizer.nextToken()*/

        // SQLITE
        var cursor = db.listGreeting()
        var name = ""
        var treatment = ""

        if(cursor.count > 0) {
            cursor.moveToFirst()

            name = cursor.getString(cursor.getColumnIndex("NAME"))
            treatment = cursor.getString(cursor.getColumnIndex("TREATMENT"))
        }
        if(treatment.equals("No Treatment")){
            lbGreeting.text = name
        }
        else{
            lbGreeting.text = treatment + " " + name
        }

    }

    fun recoverFileData(filename: String): String {
        try{
            var output = StringBuilder()
            var fi = openFileInput(filename)
            var data = fi.readBytes()
            fi.close()
            data.toString()
            return data.toString(Charset.defaultCharset())
        }
        catch (e: FileNotFoundException){
            return ""
        }
        catch (e: IOException){
            return ""
        }
    }

}