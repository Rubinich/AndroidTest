package com.example.androidtest

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class SuccessActivity : AppCompatActivity() {
    lateinit var name : String
    lateinit var textView : TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        textView = findViewById<View>(R.id.nameText) as TextView
        name = intent.getStringExtra("name").toString()
        textView.text = "$name uspjesno ste napravili 10 koraka"


    }

    @SuppressLint("QueryPermissionsNeeded")
    fun sendButton(view: View) {
        val phoneNumber = "0916218745"
        val message = textView.text.toString()

        val uri = Uri.parse("smsto:$phoneNumber")
        val smsIntent = Intent(Intent.ACTION_SENDTO, uri)
        smsIntent.putExtra("sms_body", message)

        val chooser: Intent = Intent.createChooser(smsIntent, "Share")

        if (smsIntent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        } else {
            Toast.makeText(this, "No SMS app found", Toast.LENGTH_SHORT).show()
        }
    }





}