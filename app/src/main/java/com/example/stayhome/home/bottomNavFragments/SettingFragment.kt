package com.example.stayhome.home.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    lateinit var settingBinding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingBinding = FragmentSettingBinding.inflate(inflater, container, false)
        settingBinding.tvProfile.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_profileFragment)
        }
        settingBinding.tvNotification.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_notificationFragment)
        }
        settingBinding.tvTermAndCondition.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_termAndConditionFragment)
        }
        settingBinding.tvPrivacyPolicy.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_privacyPolicyFragment)
        }
        settingBinding.tvPasswordChange.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_changePasswordFragment)
        }
        settingBinding.tvAbout.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_aboutFragment)
        }
        settingBinding.tvFeedback.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_feedbackFragment)
        }

        settingBinding.tvLogOut.setOnClickListener {
            findNavController().navigate(R.id.logOutDialogFragment)
        }


        return settingBinding.root
    }
}