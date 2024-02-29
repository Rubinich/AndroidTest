package com.example.androidtest

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class SuccessActivity : AppCompatActivity() {
    lateinit var name : String
    private lateinit var textView : TextView
    private lateinit var smallText : TextView
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        setContentView(R.layout.longer_text)

        textView = findViewById<View>(R.id.nameText) as TextView

        name = intent.getStringExtra("name").toString()
        smallText = intent.getStringExtra("success").toString()
        textView.text = name + smallText
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun sendButton(view: View) {
        val selectedRadioButtonId = findViewById<RadioGroup>(R.id.radioGroupPhoneNumbers).checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
        val phoneNumber = selectedRadioButton?.text.toString()
        val message = textView.text.toString()

        val uri = Uri.parse("smsto:$phoneNumber")
        val smsIntent = Intent(Intent.ACTION_SENDTO, uri)
        smsIntent.putExtra("sms_body", message)

        val chooser: Intent = Intent.createChooser(smsIntent, "Share")
        val smstext = findViewById<View>(R.id.no_sms) as TextView
        if (smsIntent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        } else {
            Toast.makeText(this, "$smstext", Toast.LENGTH_SHORT).show()
        }
    }






}