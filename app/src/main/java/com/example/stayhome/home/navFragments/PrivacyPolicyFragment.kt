package com.example.stayhome.home.navFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : Fragment() {


    lateinit var binding: FragmentPrivacyPolicyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPrivacyPolicyBinding.inflate(inflater, container, false)

        binding.demoText.text = getString(R.string.demo_text)

        return binding.root
    }
}