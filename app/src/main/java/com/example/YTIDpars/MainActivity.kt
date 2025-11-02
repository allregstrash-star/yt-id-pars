package com.example.YTIDpars

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootLayout = FrameLayout(this)
        rootLayout.setBackgroundColor(Color.BLACK)

        val titleView = TextView(this).apply {
            text = "🎬 YouTube Embed v0.3.002"
            setTextColor(Color.GREEN)
            textSize = 18f
            setPadding(16, 32, 16, 16)
            gravity = Gravity.CENTER_HORIZONTAL
        }

        val webView = WebView(this)
        val settings: WebSettings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()

        val videoId = "dQw4w9WgXcQ"
        val html = """
            <html>
                <body style="margin:0; background-color:black;">
                    <iframe width="100%" height="100%"
                        src="https://www.youtube.com/embed/$videoId?autoplay=1&modestbranding=1"
                        frameborder="0" allowfullscreen>
                    </iframe>
                </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)

        // Добавляем элементы в контейнер
        rootLayout.addView(webView, FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ))

        rootLayout.addView(titleView, FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ))

        setContentView(rootLayout)
    }
}
