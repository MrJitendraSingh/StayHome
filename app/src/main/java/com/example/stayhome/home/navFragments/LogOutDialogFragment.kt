package com.example.stayhome.home.navFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.stayhome.HomeActivity
import com.example.stayhome.MainActivity
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentLogOutDialogBinding
import com.example.stayhome.home.dataclass.SharedPreference
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LogOutDialogFragment : DialogFragment() {

    lateinit var logOutBinding: FragmentLogOutDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val navController = NavHostFragment.findNavController(this)
//        val viewModelProvider =
//            ViewModelProvider(navController.getViewModelStoreOwner(R.id.))

        return AlertDialog.Builder(requireActivity())
            .setTitle("LogOut")
            .setPositiveButton(R.string.yes) { _, _ ->
                 val sharedP = SharedPreference(requireContext())
                if (sharedP.getString("email") != null){
                    sharedP.allClear()
                    Toast.makeText(requireContext(), "LogOut", Toast.LENGTH_SHORT).show()
                    sharedP.setString("Validation", "one")
                }else{
                    try {
                        FirebaseAuth.getInstance().signOut();
                        LoginManager.getInstance().logOut();
                    }catch (e : Exception){

                    }
                }
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
            }
            .setNegativeButton(R.string.no) { _, _ ->  }
            .create()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        logOutBinding = FragmentLogOutDialogBinding.inflate(inflater, container, false)

        return logOutBinding.root
    }
}