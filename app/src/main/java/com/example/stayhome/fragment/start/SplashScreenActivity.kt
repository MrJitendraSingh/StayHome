package com.example.stayhome.fragment.start

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.stayhome.HomeActivity
import com.example.stayhome.MainActivity
import com.example.stayhome.databinding.ActivitySplashScreenBinding
import com.example.stayhome.home.dataclass.SharedPreference
import java.lang.Boolean


class SplashScreenActivity : AppCompatActivity() {
    lateinit var splashScreenBinding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, com.example.stayhome.R.color.red)
        splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedP = SharedPreference(this)
        val email = sharedP.getString("email")
        val password = sharedP.getString("password")

        if (email?.trim()?.isNotEmpty() == true && password?.trim()?.isNotEmpty() == true){
            Handler().postDelayed({
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }
        else {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }
    }
//    var prevStarted = "yes"
//    override fun onResume() {
//        super.onResume()
//        val sharedPreferences = getSharedPreferences("jitendra", Context.MODE_PRIVATE)
//        if (!sharedPreferences.getBoolean(prevStarted, false)) {
//            val editor = sharedPreferences.edit()
//            editor.putBoolean(prevStarted, Boolean.TRUE)
//            editor.apply()
//        } else {
//            startActivity(Intent(this, MainActivity::class.java))
//            SplashScreenActivity().finish()
//        }
//    }
}