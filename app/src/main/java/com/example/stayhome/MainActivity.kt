package com.example.stayhome

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.stayhome.databinding.ActivityMainBinding
import com.example.stayhome.fragment.start.LogInFragment
import com.example.stayhome.fragment.start.IntroFragment
import com.example.stayhome.home.dataclass.SharedPreference
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val t = "Main Activity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.red)
        val sharedP = SharedPreference(this)
//        val singUpFragment: Fragment = SignUpFragment()

        val validation = sharedP.getString("Validation")

        if (validation == "one"){
            val logInFragment: Fragment = LogInFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityFragmentContainer, logInFragment).commit()
        }
        else {
            val singUpFragment: Fragment = IntroFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityFragmentContainer, singUpFragment).commit()
        }

//        Log.e(t, "-----------------------------------------------1")
//        val quotesApi = RetrofitHelper.getInstance().create(ApiLink::class.java)
//        Log.e(t, "-----------------------------------------------2")
//        GlobalScope.launch {
//            Log.e(t, "-----------------------------------------------3")
//            val result = quotesApi.getQuotes()
//            if (result != null) {
//                Log.e(t, result.body().toString())
//            }
//        }
    }
}