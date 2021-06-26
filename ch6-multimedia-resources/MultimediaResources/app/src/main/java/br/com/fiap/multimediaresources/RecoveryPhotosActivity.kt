package br.com.fiap.multimediaresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class RecoveryPhotosActivity : AppCompatActivity() {

    lateinit var imageView1 : ImageView
    lateinit var textView1 : TextView
    lateinit var imageView2 : ImageView
    lateinit var textView2 : TextView
    lateinit var imageView3 : ImageView
    lateinit var textView3 : TextView
    lateinit var imageView4 : ImageView
    lateinit var textView4 : TextView
    lateinit var imageView5 : ImageView
    lateinit var textView5 : TextView
    lateinit var imageView6 : ImageView
    lateinit var textView6 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery_photos)

        imageView1 = findViewById(R.id.imageView1)
        textView1 = findViewById(R.id.textView1)
        imageView2 = findViewById(R.id.imageView2)
        textView2 = findViewById(R.id.textView2)
        imageView3 = findViewById(R.id.imageView3)
        textView3 = findViewById(R.id.textView3)
        imageView4 = findViewById(R.id.imageView4)
        textView4 = findViewById(R.id.textView4)
        imageView5 = findViewById(R.id.imageView5)
        textView5 = findViewById(R.id.textView5)
        imageView6 = findViewById(R.id.imageView6)
        textView6 = findViewById(R.id.textView6)

        // Declaring an array with image descriptors
        var teams = arrayOf("Lion","Penguim","Bear","Giraffe","Tiger","Mouse")

        // Adding text to visual objects
        textView1.setText(teams[0])
        textView2.setText(teams[1])
        textView3.setText(teams[2])
        textView4.setText(teams[3])
        textView5.setText(teams[4])
        textView6.setText(teams[5])

        // Declaring the variables of the images that will be presented in visual objects
        var i1 = "http://clipartbarn.com/wp-content/uploads/2017/08/Clipart-animals-free-images.png"
        var i2 = "http://clipartbarn.com/wp-content/uploads/2017/08/Animal-clipart-black-and-white-free-images-2.png"
        var i3 = "http://clipartbarn.com/wp-content/uploads/2017/08/Free-animal-clipart-for-teachers-animales-predise-ados.png"

        var i4 = "http://clipartbarn.com/wp-content/uploads/2017/08/Animal-clipart-images-on.jpg"
        var i5 = "http://clipartbarn.com/wp-content/uploads/2017/08/Free-animal-clip-art-clipart-2.gif"
        var i6 = "http://clipartbarn.com/wp-content/uploads/2017/08/Clip-art-animals-woodland-images-on.jpg"

        // Using the Glide framework to retrieve information from images that are outside the mobile device
        Glide.with(this).load(i1).into(imageView1!!)
        Glide.with(this).load(i2).into(imageView2!!)
        Glide.with(this).load(i3).into(imageView3!!)

        Glide.with(this).load(i4).into(imageView4!!)
        Glide.with(this).load(i5).into(imageView5!!)
        Glide.with(this).load(i6).into(imageView6!!)

    }
}