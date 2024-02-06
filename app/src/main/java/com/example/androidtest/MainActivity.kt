package com.example.androidtest

import android.R.attr.name
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var brojac = 0
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onCreate")
        Log.v("MyLog", "onCreate"); // Verbose
        Log.d("MyLog", "onCreate"); // Debug
        Log.i("MyLog", "onCreate"); // Info
        Log.w("MyLog", "onCreate"); // Warning
        Log.e("MyLog", "onCreate"); // Error



    }
    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStart")
        Log.v("MyLog", "onStart"); // Verbose
        Log.d("MyLog", "onStart"); // Debug
        Log.i("MyLog", "onStart"); // Info
        Log.w("MyLog", "onStart"); // Warning
        Log.e("MyLog", "onStart"); // Error
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val s1 = sh.getString("brojac", "")
        val firstName = findViewById<TextView>(R.id.textViewCounter)
        firstName.text = s1

        Log.i("MyLog", "onResume")
        Log.v("MyLog", "onResume"); // Verbose
        Log.d("MyLog", "onResume"); // Debug
        Log.i("MyLog", "onResume"); // Info
        Log.w("MyLog", "onResume"); // Warning
        Log.e("MyLog", "onResume"); // Error
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onRestart")
        Log.v("MyLog", "onRestart"); // Verbose
        Log.d("MyLog", "onRestart"); // Debug
        Log.i("MyLog", "onRestart"); // Info
        Log.w("MyLog", "onRestart"); // Warning
        Log.e("MyLog", "onRestart"); // Error
    }
    override fun onPause() {
        super.onPause()

        // Ensure 'brojac' is valid and retrieve its value
        val brojacValue = findViewById<TextView>(R.id.textViewCounter)?.text?.toString() ?: ""

        // Create or obtain a SharedPreferences instance
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the brojac value using a clear key
        editor.putString("brojac", brojacValue)
        editor.apply() // Apply changes asynchronously



        Log.i("MyLog", "onPause")
        Log.v("MyLog", "onPause"); // Verbose
        Log.d("MyLog", "onPause"); // Debug
        Log.i("MyLog", "onPause"); // Info
        Log.w("MyLog", "onPause"); // Warning
        Log.e("MyLog", "onPause"); // Error
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStop")
        Log.v("MyLog", "onStop"); // Verbose
        Log.d("MyLog", "onStop"); // Debug
        Log.i("MyLog", "onStop"); // Info
        Log.w("MyLog", "onStop"); // Warning
        Log.e("MyLog", "onStop"); // Error
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")
        Log.v("MyLog", "onDestroy"); // Verbose
        Log.d("MyLog", "onDestroy"); // Debug
        Log.i("MyLog", "onDestroy"); // Info
        Log.w("MyLog", "onDestroy"); // Warning
        Log.e("MyLog", "onDestroy"); // Error
    }

    fun setOnClickListenerUp(view: View) {
        brojac++
        val firstName = findViewById<TextView>(R.id.textViewCounter)
        firstName.text= "$brojac"
    }
    fun setOnClickListenerDown(view: View) {
        if(brojac>0) {
            brojac--
            val firstName = findViewById<TextView>(R.id.textViewCounter)
            firstName.text = "$brojac"
        }
    }
}