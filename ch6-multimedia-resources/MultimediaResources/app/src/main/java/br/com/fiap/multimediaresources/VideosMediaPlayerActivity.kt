package br.com.fiap.multimediaresources

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideosMediaPlayerActivity : AppCompatActivity() {

    lateinit var btnonce: ImageButton
    lateinit var btnconti: ImageButton
    lateinit var btnstop: ImageButton
    lateinit var btnplay: ImageButton
    lateinit var vv: VideoView
    lateinit var progrss: ProgressBar
    private var uri: Uri? = null
    private var isContinuously = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos_media_player)

        btnonce = findViewById(R.id.btnonce)
        btnconti = findViewById(R.id.btnconti)
        btnstop = findViewById(R.id.btnstop)
        btnplay = findViewById(R.id.btnplay)
        vv = findViewById(R.id.vv)
        progrss = findViewById(R.id.progrss)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(vv)

        val uriPath = "https://ia800209.us.archive.org/24/items/WildlifeSampleVideo/Wildlife.mp4"

        uri = Uri.parse(uriPath)

        vv!!.setOnCompletionListener {
            if (isContinuously) {
                vv!!.start()
            }
        }

        btnstop!!.setOnClickListener { vv!!.pause() }
        btnplay!!.setOnClickListener { vv!!.start() }

        btnonce!!.setOnClickListener {
            isContinuously = false
            progrss!!.visibility = View.VISIBLE
            vv!!.setMediaController(mediaController)
            vv!!.setVideoURI(uri)
            vv!!.requestFocus()
            vv!!.start()
        }

        btnconti!!.setOnClickListener {
            isContinuously = true
            progrss!!.visibility = View.VISIBLE
            vv!!.setMediaController(mediaController)
            vv!!.setVideoURI(uri)
            vv!!.requestFocus()
            vv!!.start()
        }

        vv!!.setOnPreparedListener { progrss!!.visibility = View.GONE }

    }
}