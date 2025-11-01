package com.example.YTIDpars

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.allowFileAccess = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        val videoId = "dQw4w9WgXcQ" // можешь заменить на любое ID
        val baseUrl = "https://localhost"

        val html = """
            <!doctype html>
            <html>
              <head>
                <meta name="viewport" content="width=device-width,initial-scale=1">
                <style>
                  html,body{height:100%;margin:0;background:#000}
                  iframe{position:absolute;left:0;top:0;width:100%;height:100%;border:0;}
                </style>
              </head>
              <body>
                <iframe id="ytplayer"
                    src="https://www.youtube-nocookie.com/embed/$videoId?rel=0&origin=$baseUrl"
                    referrerpolicy="strict-origin-when-cross-origin"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen>
                </iframe>
              </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(baseUrl, html, "text/html", "utf-8", null)
    }

    override fun onDestroy() {
        webView.loadUrl("about:blank")
        webView.destroy()
        super.onDestroy()
    }
}
