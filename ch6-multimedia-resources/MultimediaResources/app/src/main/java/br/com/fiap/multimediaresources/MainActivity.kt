package br.com.fiap.multimediaresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun accessPhotoRecovery(view: View) {
        val intent = Intent(this, RecoveryPhotosActivity::class.java)
        startActivity(intent)
    }

    fun accessMap(view: View) {
        val intent = Intent(this, UsingMapsActivity::class.java)
        startActivity(intent)
    }

    fun accessUserLocation(view: View) {
        val intent = Intent(this, UserLocationActivity::class.java)
        startActivity(intent)
    }

    fun accessRoutes(view: View) {
        val intent = Intent(this, FirstRouteActivity::class.java)
        startActivity(intent)
    }

    fun accessPointsOfInterest(view: View) {
        val intent = Intent(this, PointsOfInterestActivity::class.java)
        startActivity(intent)
    }

    fun accessWebView(view: View) {
        val intent = Intent(this, UsingWebViewActivity::class.java)
        startActivity(intent)
    }

    fun accessAudio(view: View) {
        val intent = Intent(this, SoundsAudioActivity::class.java)
        startActivity(intent)
    }

    fun accessVideo(view: View) {
        val intent = Intent(this, VideosMediaPlayerActivity::class.java)
        startActivity(intent)
    }

    fun accessAnimation(view: View) {
        val intent = Intent(this, AnimationActivity::class.java)
        startActivity(intent)
    }

}