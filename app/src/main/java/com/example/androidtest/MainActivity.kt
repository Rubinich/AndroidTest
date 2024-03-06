package com.example.androidtest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.data.User
import com.example.androidtest.data.UserViewModel
import java.time.LocalTime
import java.util.Locale


class MainActivity : AppCompatActivity() {
    var brojac = 0
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mUserViewModel: UserViewModel
    companion object {
        const val SCORE_KEY = "score_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_SHORT).show()
        /*Log.v("MyLog", "onCreate");
        Log.d("MyLog", "onCreate");
        Log.i("MyLog", "onCreate");
        Log.w("MyLog", "onCreate");
        Log.e("MyLog", "onCreate");*/
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        brojac = sharedPreferences.getInt(SCORE_KEY, 0)
        val textView = findViewById<TextView>(R.id.textViewCounter)
        textView.text = "$brojac"
        setSupportActionBar(findViewById(R.id.my_toolbar))

        registerForContextMenu(textView)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        /*Log.v("MyLog", "onStart");
        Log.d("MyLog", "onStart");
        Log.i("MyLog", "onStart");
        Log.w("MyLog", "onStart");
        Log.e("MyLog", "onStart"); */
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()


        /*Log.v("MyLog", "onResume");
        Log.d("MyLog", "onResume");
        Log.i("MyLog", "onResume");
        Log.w("MyLog", "onResume");
        Log.e("MyLog", "onResume");*/
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        /*Log.v("MyLog", "onRestart");
        Log.d("MyLog", "onRestart");
        Log.i("MyLog", "onRestart");
        Log.w("MyLog", "onRestart");
        Log.e("MyLog", "onRestart");*/
    }
    override fun onPause() {
        super.onPause()
        val textView = findViewById<TextView>(R.id.textViewCounter)
        val brojacValue = textView.text.toString().toIntOrNull() ?: 0
        val editor = sharedPreferences.edit()
        editor.putInt(SCORE_KEY, brojacValue)
        editor.apply()

        /*Log.v("MyLog", "onPause");
        Log.d("MyLog", "onPause");
        Log.i("MyLog", "onPause");
        Log.w("MyLog", "onPause");
        Log.e("MyLog", "onPause");*/
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()

        /*Log.v("MyLog", "onStop");
        Log.d("MyLog", "onStop");
        Log.i("MyLog", "onStop");
        Log.w("MyLog", "onStop");
        Log.e("MyLog", "onStop");*/
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()

       /* Log.v("MyLog", "onDestroy");
        Log.d("MyLog", "onDestroy");
        Log.i("MyLog", "onDestroy");
        Log.w("MyLog", "onDestroy");
        Log.e("MyLog", "onDestroy");*/
    }

    fun setOnClickListenerUp(view: View) {
        insertDataToDatabase()
        brojac++
        if(brojac%10 == 0){
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.restore_counter -> {
                brojac = 0
                val steps = findViewById<TextView>(R.id.textViewCounter)
                steps.text = "$brojac"
                return true
            }
            R.id.analytics ->{
                val intent = Intent(this, AnalyticsActivity::class.java)
                startActivity(intent)
            }
            R.id.croatian -> {
                changeLanguage(this, "hr")
                recreate()
                return true
            }
            R.id.english -> {
                changeLanguage(this, "en-us")
                recreate()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun insertDataToDatabase() {
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val name = findViewById<TextView>(R.id.plainTextName).text.toString()
        val time = LocalTime.now()
        val timeString = time.toString() // this stores the value of time
        if(TextUtils.isEmpty(name)){
            Log.e("Database", "User didn't enter it's name")
            brojac --
            Toast.makeText(applicationContext, "Alojz", Toast.LENGTH_SHORT).show()
        }else{
            val user = User(0, name, timeString, brojac)
            mUserViewModel.addUser(user)
        }
    }

    @Suppress("DEPRECATION")
    private fun changeLanguage(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        res.updateConfiguration(config, res.displayMetrics)
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_float, menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.couterReset -> {
                resetCounter(item)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun resetCounter(item: MenuItem) {
        brojac = 0
        val textViewCounter = findViewById<TextView>(R.id.textViewCounter)
        textViewCounter.text = "$brojac"
    }


}