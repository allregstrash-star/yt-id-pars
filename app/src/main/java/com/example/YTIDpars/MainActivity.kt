package com.example.YTiDPars

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var pasteAndGoButton: Button
    private lateinit var infoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pasteAndGoButton = findViewById(R.id.pasteAndGoButton)
        infoTextView = findViewById(R.id.infoTextView)

        pasteAndGoButton.setOnClickListener {
            generateAndShowHtml()
        }
    }

    private fun generateAndShowHtml() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val pasteData = clipboard.primaryClip?.getItemAt(0)?.text?.toString().orEmpty()

        if (pasteData.isEmpty()) {
            infoTextView.text = "Clipboard empty!"
            Toast.makeText(this, "Нечего вставлять", Toast.LENGTH_SHORT).show()
            return
        }
        
        Toast.makeText(this, "Read link: $pasteData", Toast.LENGTH_SHORT).show()

        val videoId = extractYouTubeId(pasteData)

        if (videoId == null) {
            infoTextView.text = "can't extract yt ID video:\n$pasteData"
            Toast.makeText(this, "can't pars yt link", Toast.LENGTH_LONG).show()
            return
        }

        val htmlContent = """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>TEST YTIDpars</title>
                </head>
                <body>
                    <iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/$videoId" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                </body>
            </html>
        """.trimIndent()

        infoTextView.text = htmlContent
        Toast.makeText(this, "HTML built!", Toast.LENGTH_LONG).show()
    }

    private fun extractYouTubeId(youtubeUrl: String): String? {
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2Fvideos%2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(youtubeUrl)
        return if (matcher.find()) {
            matcher.group()
        } else {
            null
        }
    }
}
