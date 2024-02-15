package com.example.androidtest
import android.content.SharedPreferences
import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var brojac = 0
    private lateinit var sharedPreferences: SharedPreferences
    companion object {
        const val SCORE_KEY = "score_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_SHORT).show()
        Log.v("MyLog", "onCreate");
        Log.d("MyLog", "onCreate");
        Log.i("MyLog", "onCreate");
        Log.w("MyLog", "onCreate");
        Log.e("MyLog", "onCreate");
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        brojac = sharedPreferences.getInt(SCORE_KEY, 0)
        val textView = findViewById<TextView>(R.id.textViewCounter)
        textView.text = "$brojac"
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.v("MyLog", "onStart"); // Verbose
        Log.d("MyLog", "onStart"); // Debug
        Log.i("MyLog", "onStart"); // Info
        Log.w("MyLog", "onStart"); // Warning
        Log.e("MyLog", "onStart"); // Error
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()


        Log.v("MyLog", "onResume"); // Verbose
        Log.d("MyLog", "onResume"); // Debug
        Log.i("MyLog", "onResume"); // Info
        Log.w("MyLog", "onResume"); // Warning
        Log.e("MyLog", "onResume"); // Error
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.v("MyLog", "onRestart"); // Verbose
        Log.d("MyLog", "onRestart"); // Debug
        Log.i("MyLog", "onRestart"); // Info
        Log.w("MyLog", "onRestart"); // Warning
        Log.e("MyLog", "onRestart"); // Error
    }
    override fun onPause() {
        super.onPause()
        // Save updated brojac value to SharedPreferences
        val textView = findViewById<TextView>(R.id.textViewCounter)
        val brojacValue = textView.text.toString().toIntOrNull() ?: 0
        val editor = sharedPreferences.edit()
        editor.putInt(SCORE_KEY, brojacValue)
        editor.apply()




        Log.v("MyLog", "onPause"); // Verbose
        Log.d("MyLog", "onPause"); // Debug
        Log.i("MyLog", "onPause"); // Info
        Log.w("MyLog", "onPause"); // Warning
        Log.e("MyLog", "onPause"); // Error
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()

        Log.v("MyLog", "onStop"); // Verbose
        Log.d("MyLog", "onStop"); // Debug
        Log.i("MyLog", "onStop"); // Info
        Log.w("MyLog", "onStop"); // Warning
        Log.e("MyLog", "onStop"); // Error
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()

        Log.v("MyLog", "onDestroy"); // Verbose
        Log.d("MyLog", "onDestroy"); // Debug
        Log.i("MyLog", "onDestroy"); // Info
        Log.w("MyLog", "onDestroy"); // Warning
        Log.e("MyLog", "onDestroy"); // Error
    }

    fun setOnClickListenerUp(view: View) {
        brojac++
        if(brojac == 10){
            brojac = 0
            val intent = Intent(this, SuccessActivity::class.java).apply{ putExtra ("name", findViewById<TextView>(R.id.plainTextName).text.toString())
            }
            startActivity(intent)
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}