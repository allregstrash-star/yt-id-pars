package com.example.YTIDpars

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        setContentView(webView)

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        // Отключаем safe browsing, чтобы WebView не блокировал встраиваемые iframe (тестовый режим)
        settings.safeBrowsingEnabled = false

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }

        // === версия (меняй при следующих билдах) ===
        val versionLabel = "v0.3.005"

        val html = """
            <!DOCTYPE html>
            <html>
              <head>
                <meta charset="utf-8">
                <title>YouTube Embed $versionLabel</title>
                <meta name="viewport" content="width=device-width,initial-scale=1">
                <style>
                  body {
                    margin: 0;
                    background-color: #000;
                    color: #00ff56;
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    justify-content: center;
                    height: 100vh;
                    font-family: sans-serif;
                  }
                  h2 { margin: 8px 0 16px 0; font-size: 20px; }
                  .player {
                    width: 90vw;
                    height: 50vw;
                    max-width: 640px;
                    max-height: 360px;
                    border-radius: 12px;
                    overflow: hidden;
                    box-shadow: 0 0 30px rgba(0,255,100,0.12);
                  }
                  iframe { width:100%; height:100%; border:0; display:block; }
                </style>
              </head>
              <body>
                <h2>🎬 YouTube Embed $versionLabel</h2>
                <div class="player">
                  <iframe
                    src="https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ?autoplay=1&modestbranding=1"
                    title="YouTube video player"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                    allowfullscreen>
                  </iframe>
                </div>
              </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
    }
}
