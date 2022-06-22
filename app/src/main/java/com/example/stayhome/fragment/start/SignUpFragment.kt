package com.example.stayhome.fragment.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stayhome.HomeActivity
import com.example.stayhome.R
import com.example.stayhome.TextObserver.textCom.checkUp
import com.example.stayhome.databinding.FragmentSignUpBinding
import com.example.stayhome.home.dataclass.SharedPreference
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val support = requireActivity().supportFragmentManager
//        support.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

    }
    private val TAG = "FacebookLogin"
    private val RC_SIGN_IN = 12345
    lateinit var mCallbackManager: CallbackManager
    lateinit var mAuth: FirebaseAuth
    lateinit var button : Button

    lateinit var signUpBinding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        val sharedP = SharedPreference(requireContext())
        val supportFragment = requireActivity().supportFragmentManager

        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
//        signUpBinding.btnFaceBookSignUp.setReadPermissions("email", "public_profile");
//
//        signUpBinding.btnFaceBookSignUp.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult?> {
//            override fun onCancel() {
//                Log.d(TAG, "facebook:onCancel")
//            }
//
//            override fun onError(error: FacebookException) {}
//            override fun onSuccess(result: LoginResult?) {
//                Log.d(TAG, "facebook:onSuccess:$result")
//                handleFacebookAccessToken(result!!.accessToken)
//            }
//        })


        signUpBinding.btnSignUp.setOnClickListener {

            val name = signUpBinding.tieName.text.toString()
            val lastName = signUpBinding.tieLastName.text.toString()
            val email = signUpBinding.tieEmailId.text.toString()
            val password = signUpBinding.tiePassword.text.toString()
            val conformPassword = signUpBinding.tieConformPassword.text.toString()


            if (name.trim().isNotEmpty() && email.trim().isNotEmpty() && password.trim().isNotEmpty() && conformPassword.trim().isNotEmpty() &&
                lastName.trim().isNotEmpty() && password == conformPassword && signUpBinding.cbSignUp.isChecked && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                sharedP.setString("name", name)
                sharedP.setString("lastName", lastName)
                sharedP.setString("email",email)
                sharedP.setString("password", password)
                sharedP.setString("conformPassword", conformPassword)
//                val logInFragment: Fragment = LogInFragment()
//                val supportFragment = requireActivity().supportFragmentManager.beginTransaction()
//                val bundle = Bundle()
//                logInFragment.arguments = bundle
//                supportFragment.replace(R.id.mainActivityFragmentContainer, logInFragment).commit()
//                supportFragment.addToBackStack(null)

                startActivity(Intent(requireContext(), HomeActivity::class.java))

            }else{
                signUpBinding.apply {
                checkUp(tieName, tilNameContainer1, "Please enter name")
                checkUp(tieLastName, tilLastNameContainer2, "Please enter last name")
                checkUp(tieEmailId, tilEmailContainer3, "Please enter email")
                if (!Patterns.EMAIL_ADDRESS.matcher(signUpBinding.tieEmailId.text.toString()).matches() && email.trim().isNotEmpty())
                {
                    signUpBinding.tilEmailContainer3.helperText = "Invalid email"
                }
                checkUp(tiePassword, tilPasswordContainer4, "Please enter password")
                checkUp(tieConformPassword, tilConformPasswordContainer5, "Please conform password")
                if (password != conformPassword){
                    tilConformPasswordContainer5.helperText = "Password miss match"
                }
                if (!cbSignUp.isChecked){
                    Toast.makeText(requireContext(), "Accept Privacy Policy", Toast.LENGTH_SHORT).show()
                }
            }
            }
        }
        val text = "I agree with <font color=#cc0033>Privacy Policy</font>"

        //stay-home-18b21   521937629374295
        //I Agree With Privacy and Policy
        signUpBinding.tvSignUp1.text = Html.fromHtml(text)

        signUpBinding.btnSignIn.setOnClickListener {
            Log.e("Sign In", "----------------------------------------------")
            val validation = sharedP.getString("Validation")

            if (validation == "one"){
                supportFragment.popBackStack()
            }
            else {
                val logInFragment: Fragment = LogInFragment()
                supportFragment.beginTransaction()
                    .replace(R.id.mainActivityFragmentContainer, logInFragment).addToBackStack(null)
                    .commit()
            }
//            startActivity(Intent(requireContext(), HomeActivity::class.java))
        }

//        val id = resources.getStringArray(R.array.id)
//        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, id)
//        signUpBinding.tieName.setAdapter(arrayAdapter)
//        signUpBinding.tieEmailId.setAdapter(arrayAdapter)
//        val lastName = resources.getStringArray(R.array.LastName)
//        val arrayLastName = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, lastName)
//        signUpBinding.tieLastName.setAdapter(arrayLastName)

        return signUpBinding.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            Log.d(TAG, "Currently Signed in: " + currentUser.email)
            Toast.makeText(requireContext(), "Currently Logged in: " + currentUser.email, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity()
        ) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                val user = mAuth.currentUser
                Toast.makeText(requireContext(), "Authentication Succeeded.", Toast.LENGTH_SHORT).show()
            } else {
                // If sign-in fails, a message will display to the user.
                Log.w(TAG, "signInWithCredential:failure", task.getException())
                Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}