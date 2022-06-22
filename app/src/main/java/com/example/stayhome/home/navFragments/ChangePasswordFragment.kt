package com.example.stayhome.home.navFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.stayhome.databinding.FragmentChangePasswordBinding
import com.example.stayhome.home.dataclass.SharedPreference

class ChangePasswordFragment : Fragment() {
    lateinit var binding: FragmentChangePasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)

        val sharedP = SharedPreference(requireContext())

        binding.tvSave.setOnClickListener {
            val oldPassword = binding.tieOldPassword.text.toString()
            val newPassword = binding.tieNewPassword.text.toString()
            val conformPassword = binding.tieConformPassword.text.toString()
            val password = sharedP.getString("password")
            if (oldPassword == password && oldPassword.trim().isNotEmpty() && newPassword.trim().isNotEmpty() && conformPassword.trim().isNotEmpty()
                && newPassword == conformPassword){
                Toast.makeText(requireContext(), "Save new password", Toast.LENGTH_SHORT).show()
                sharedP.setString("password", conformPassword)
                requireActivity().finish()
            }else{
                if (oldPassword != password){
                    binding.tilOldPasswordContainer.helperText = "Invalid password"
                }
                if (oldPassword.trim().isEmpty()){
                    binding.tilOldPasswordContainer.helperText = "Enter old password"
                }
                if (newPassword.trim().isEmpty()){
                    binding.tilNewPasswordContainer.helperText = "Enter new password"
                }
                if (conformPassword.trim().isEmpty()){
                    binding.tilConformPasswordContainer.helperText = "Conform password"
                }
                if (conformPassword != newPassword){
                    binding.tilConformPasswordContainer.helperText = "Password mismatch"
                }

            }
        }

        binding.tvClear.setOnClickListener {
            binding.tieOldPassword.text?.clear()
            binding.tieNewPassword.text?.clear()
            binding.tieConformPassword.text?.clear()
            binding.tilOldPasswordContainer.helperText = null
            binding.tilNewPasswordContainer.helperText = null
            binding.tilConformPasswordContainer.helperText = null
        }

        return binding.root
    }
}