package br.com.fiap.multimediaresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaPlayer
import android.view.View

class SoundsAudioActivity : AppCompatActivity() {

    var cow: MediaPlayer? = null
    var dog: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sounds_audio)

        cow = MediaPlayer.create(this, R.raw.cow)
        dog = MediaPlayer.create(this, R.raw.dog)
    }

    fun soundCow(view: View) {
        //if(dog != null) {
        //    dog!!.stop()
        //}
        cow!!.start()
    }

    fun soundDog(view: View) {
        //if(cow != null) {
        //    cow!!.stop()
        //}
        dog!!.start()
    }
}