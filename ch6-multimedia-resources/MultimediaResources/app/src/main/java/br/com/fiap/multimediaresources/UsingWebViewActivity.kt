package br.com.fiap.multimediaresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class UsingWebViewActivity : AppCompatActivity() {

    lateinit var webview : WebView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_using_web_view)

        webview = findViewById(R.id.webview)

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }

        webview.getSettings().setJavaScriptEnabled(true)
        webview.loadUrl("https://www.fiap.com.br/")
    }
}