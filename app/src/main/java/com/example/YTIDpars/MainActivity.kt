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
        settings.safeBrowsingEnabled = false
        settings.userAgentString =
            "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Mobile Safari/537.36"

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean = false
        }

        // версия
        val versionLabel = "v0.3.006"

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
                  iframe { width:100%; height:100%; border:0; display:block; background:#111; }
                </style>
              </head>
              <body>
                <h2>🎬 YouTube Embed $versionLabel</h2>
                <div class="player">
                  <iframe
                    src="https://www.youtube.com/embed/dQw4w9WgXcQ?modestbranding=1&rel=0"
                    referrerpolicy="strict-origin-when-cross-origin"
                    allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                    allowfullscreen>
                  </iframe>
                </div>
              </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL("https://www.youtube.com", html, "text/html", "utf-8", null)
    }
}
