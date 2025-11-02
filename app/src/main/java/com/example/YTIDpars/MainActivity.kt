package com.example.YTIDpars

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)

        // Включаем базовые настройки
        webView.settings.javaScriptEnabled = true

        // Просто отображаем локальный HTML
        val html = """
            <html>
              <head>
                <meta charset="utf-8">
                <style>
                  body {
                    background-color: #121212;
                    color: white;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                    font-family: sans-serif;
                    font-size: 24px;
                  }
                </style>
              </head>
              <body>
                ✅ <b>WebView работает!</b>
              </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
    }
}
