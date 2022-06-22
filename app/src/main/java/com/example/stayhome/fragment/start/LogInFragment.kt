package com.example.stayhome.fragment.start

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.stayhome.HomeActivity
import com.example.stayhome.MainActivity
import com.example.stayhome.R
import com.example.stayhome.TextObserver
import com.example.stayhome.databinding.FragmentLogInBinding
import com.example.stayhome.home.dataclass.SharedPreference
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LogInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val support = requireActivity().supportFragmentManager
//        if (support.backStackEntryCount > 1){
//            support.popBackStack()
//        }
    }
    private val TAG = "FacebookLogin"
    private val RC_SIGN_IN = 12345
    lateinit var mCallbackManager: CallbackManager
    lateinit var mAuth: FirebaseAuth
    lateinit var button : Button
    lateinit var logInBinding: FragmentLogInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logInBinding = FragmentLogInBinding.inflate(inflater, container, false)
        val support = requireActivity().supportFragmentManager
        val sharedP = SharedPreference(requireContext())

        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
//        logInBinding.btnFaceBookSignIn.setReadPermissions("email", "public_profile");
//
//        logInBinding.btnFaceBookSignIn.registerCallback(mCallbackManager, object :
//            FacebookCallback<LoginResult?> { override fun onCancel() {
//                Log.d(TAG, "facebook:onCancel")
//            }
//
//            override fun onError(error: FacebookException) {}
//            override fun onSuccess(result: LoginResult?) {
//                Log.d(TAG, "facebook:onSuccess:$result")
//                handleFacebookAccessToken(result!!.accessToken)
//            }
//        })

        logInBinding.apply {
            btnLogIn.setOnClickListener {

                val enteredEmail = tieEmailId.text.toString()
                val enteredPassword = tiePassword.text.toString()
                val email = sharedP.getString("email")
                val password = sharedP.getString("password")

                if (enteredEmail.trim().isNotEmpty() && enteredPassword.trim().isNotEmpty()){
                    sharedP.setString("email",enteredEmail)
                    sharedP.setString("password", enteredPassword)
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                else{
                    TextObserver.checkUp(tieEmailId, tilEmailContainer, "Please enter email")
                    if (!Patterns.EMAIL_ADDRESS.matcher(tieEmailId.text.toString()).matches() && tieEmailId.text.toString().trim().isNotEmpty())
                    {
                        tilEmailContainer.helperText = "Invalid email"
                    }
                    TextObserver.checkUp(tiePassword, tilPasswordContainer, "Please enter password")
                }
            }
            val validation = sharedP.getString("Validation")
            btnSignUp.setOnClickListener {
                if (validation == "one"){
                    support.beginTransaction().replace(R.id.mainActivityFragmentContainer, SignUpFragment()).addToBackStack(null).commit()
                }
                else {
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }



        return logInBinding.root
    }

//    override fun onStart() {
//        super.onStart()
//        val currentUser = mAuth.currentUser
//        if (currentUser != null) {
//            Log.d(TAG, "Currently Signed in: " + currentUser.email)
//            Toast.makeText(requireContext(), "Currently Logged in: " + currentUser.email, Toast.LENGTH_LONG).show()
//        }
//    }

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
//                startActivity(Intent(requireContext(), HomeActivity::class.java))
//                requireActivity().finish()
                Toast.makeText(requireContext(), "Authentication Succeeded.", Toast.LENGTH_SHORT).show()
            } else {
                // If sign-in fails, a message will display to the user.
                Log.w(TAG, "signInWithCredential:failure", task.getException())
                Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}