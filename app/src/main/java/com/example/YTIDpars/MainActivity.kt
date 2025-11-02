package com.example.YTIDpars

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()

        // 🔹 Версия приложения (меняй при каждом билде, чтобы проверять обновление)
        val versionLabel = "v0.3.001"

        // 🔸 HTML код для отображения плеера и версии
        val html = """
            <!DOCTYPE html>
            <html lang="ru">
            <head>
              <meta charset="UTF-8">
              <title>YouTube Embed Test $versionLabel</title>
              <style>
                body {
                  margin: 0;
                  background-color: #000;
                  color: #fff;
                  display: flex;
                  flex-direction: column;
                  align-items: center;
                  justify-content: center;
                  height: 100vh;
                  font-family: sans-serif;
                }
                h2 {
                  margin-bottom: 20px;
                  font-size: 1.2rem;
                  color: #0f0;
                }
                iframe {
                  width: 90vw;
                  height: 50vw;
                  max-width: 560px;
                  max-height: 315px;
                  border: none;
                  border-radius: 12px;
                  box-shadow: 0 0 20px rgba(0,255,0,0.3);
                }
              </style>
            </head>
            <body>
              <h2>🎬 YouTube Embed $versionLabel</h2>
              <iframe 
                src="https://www.youtube.com/embed/dQw4w9WgXcQ" 
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                allowfullscreen>
              </iframe>
            </body>
            </html>
        """.trimIndent()

        // 🧩 Загружаем HTML в WebView
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
    }
}
