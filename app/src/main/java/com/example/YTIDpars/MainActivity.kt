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

class MainActivity : AppCompatActivity() {

    private lateinit var pasteAndGoButton: Button
    private lateinit var infoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pasteAndGoButton = findViewById(R.id.pasteAndGoButton)
        infoTextView = findViewById(R.id.infoTextView)

        pasteAndGoButton.setOnClickListener {
            readClipboardAndAct()
        }
    }

    private fun readClipboardAndAct() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var pasteData = ""

        if (clipboard.hasPrimaryClip()) {
            val item: ClipData.Item? = clipboard.primaryClip?.getItemAt(0)
            pasteData = item?.text?.toString().orEmpty()
        }

        if (pasteData.isEmpty()) {
            infoTextView.text = "Буфер обмена пуст!"
            Toast.makeText(this, "Нечего вставлять", Toast.LENGTH_SHORT).show()
            return
        }

        infoTextView.text = "Вставлено: $pasteData"

        try {
            var url = pasteData
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "Не удалось открыть как ссылку", Toast.LENGTH_LONG).show()
        }
    }
}