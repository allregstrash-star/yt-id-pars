package com.example.YTIDpars

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)

        // Настройки WebView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webChromeClient = WebChromeClient()

        // HTML с YouTube embed
        val html = """
            <html>
              <head>
                <meta charset="utf-8">
                <style>
                  body {
                    background-color: #000;
                    margin: 0;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                  }
                  iframe {
                    width: 90vw;
                    height: 50vh;
                    border: none;
                    border-radius: 12px;
                  }
                </style>
              </head>
              <body>
                <iframe 
                    src="https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ"
                    referrerpolicy="strict-origin-when-cross-origin"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen>
                </iframe>
              </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(
            "https://www.youtube.com", html, "text/html", "utf-8", null
        )
    }
}
