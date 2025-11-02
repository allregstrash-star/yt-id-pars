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
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        settings.userAgentString =
            "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Mobile Safari/537.36"

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean = false
        }

        val versionLabel = "v0.3.007"

        val html = """
            <!DOCTYPE html>
            <html>
              <head>
                <meta charset="utf-8">
                <title>YouTube Embed Test $versionLabel</title>
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
                    text-align: center;
                  }
                  h2 { margin: 10px 0 20px 0; font-size: 22px; }
                  .preview {
                    position: relative;
                    width: 90vw;
                    max-width: 640px;
                    border-radius: 14px;
                    overflow: hidden;
                    box-shadow: 0 0 25px rgba(0,255,100,0.2);
                  }
                  img {
                    width: 100%;
                    display: block;
                  }
                  button {
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    background: rgba(0,255,50,0.9);
                    border: none;
                    border-radius: 50%;
                    width: 70px;
                    height: 70px;
                    color: #000;
                    font-size: 24px;
                    font-weight: bold;
                    box-shadow: 0 0 15px rgba(0,255,0,0.6);
                  }
                </style>
              </head>
              <body>
                <h2>🎬 YouTube Embed $versionLabel</h2>
                <div class="preview">
                  <img src="https://img.youtube.com/vi/dQw4w9WgXcQ/hqdefault.jpg" alt="Preview">
                  <button onclick="alert('🎥 Видео не загружается из-за ограничений WebView')">▶</button>
                </div>
              </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
    }
}
