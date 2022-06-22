package com.example.stayhome.home.navFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentFeedbackBinding

class FeedbackFragment : Fragment() {

    lateinit var binding: FragmentFeedbackBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedbackBinding.inflate(inflater, container, false)

        val arr =  resources.getStringArray(R.array.category)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_item, arr)

        binding.category.threshold = 0
        binding.category.setAdapter(adapter)

        binding.btnSend.setOnClickListener {

            val category = binding.category.text.toString()
            val feedback = binding.tieFeedback.text.toString()

            Toast.makeText(requireContext(), category+ feedback, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}