package com.example.YTiDPars

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
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
            createAndOpenHtmlFromClipboard()
        }
    }

    private fun createAndOpenHtmlFromClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val pasteData = clipboard.primaryClip?.getItemAt(0)?.text?.toString().orEmpty()

        if (pasteData.isEmpty()) {
            infoTextView.text = "Буфер обмена пуст!"
            Toast.makeText(this, "Нечего вставлять", Toast.LENGTH_SHORT).show()
            return
        }

        infoTextView.text = "Вставлено: $pasteData"
        val videoId = extractYouTubeId(pasteData)

        if (videoId == null) {
            Toast.makeText(this, "Это не похоже на ссылку YouTube", Toast.LENGTH_LONG).show()
            return
        }

        val htmlContent = """
            <!DOCTYPE html>
            <html>
                <head>
                    <title>Фан-страница Яндекса</title>
                </head>
                <body>
                    <iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/$videoId" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                </body>
            </html>
        """.trimIndent()

        try {
            val appSpecificDir = getExternalFilesDir(null)
            val file = File(appSpecificDir, "youtube_page_$videoId.html")
            file.writeText(htmlContent)

            Toast.makeText(this, "Файл сохранен! Открываю в браузере...", Toast.LENGTH_SHORT).show()
            infoTextView.text = "HTML-файл для видео $videoId создан!"

            // ----- НОВЫЙ БЛОК КОДА ДЛЯ ЗАПУСКА БРАУЗЕРА -----
            val authority = "com.example.YTiDPars.fileprovider"
            val fileUri: Uri = FileProvider.getUriForFile(this, authority, file)

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(fileUri, "text/html")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            startActivity(intent)
            // ----------------------------------------------

        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
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
