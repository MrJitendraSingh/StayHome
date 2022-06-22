package com.example.stayhome.home.navFragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stayhome.MainActivity
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentProfileBinding
import com.example.stayhome.home.dataclass.SharedPreference
import com.facebook.drawee.backends.pipeline.Fresco

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(requireContext());
    }
    private lateinit var profileBinding: FragmentProfileBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)

        val sharedP = SharedPreference(requireContext())
        val name = sharedP.getString("name")
        val lastName = sharedP.getString("lastName")
        val email = sharedP.getString("email")
        profileBinding.apply {

            profileImage.setActualImageResource(R.drawable.shopimage)
            enterName.setText("$name $lastName")
            enterEmail.setText(email)
            enterUserName.setText("sh101$name")

            btnEdit.setOnClickListener {
                enterMobile.setBackgroundColor(R.color.text_back)
                enterDateOfBirth.setBackgroundColor(R.color.text_back)
                Handler().postDelayed({
                    enterMobile.background = null
                    enterDateOfBirth.background = null
                }, 1000)
                enterMobile.isEnabled = true
                enterDateOfBirth.isEnabled = true
            }
            btnSave.setOnClickListener{

                enterMobile.setText(enterMobile.text.toString())
                enterDateOfBirth.setText(enterDateOfBirth.text.toString())

                enterMobile.isEnabled = false
                enterDateOfBirth.isEnabled = false
//                val sendIntent: Intent = Intent().apply {
//                    action = Intent.ACTION_SEND
//                    putExtra(Intent.EXTRA_TEXT, name.toString())
//                    type = "text/plain"
//                }
//
//                val shareIntent = Intent.createChooser(sendIntent, null)
//                startActivity(shareIntent)
            }
        }
        return profileBinding.root
    }
}