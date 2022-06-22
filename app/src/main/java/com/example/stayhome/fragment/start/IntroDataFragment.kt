package com.example.stayhome.fragment.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentIntroDataBinding


class IntroDataFragment : Fragment() {


    lateinit var viewPagerAdapter: IntroScreenAdapter
    private var title: String? = null
    private var page = 0
    open fun newInstance(page: Int, title: String?): IntroDataFragment? {
        val fragmentFirst = IntroDataFragment()
        val args = Bundle()
        args.putInt("someInt", page)
        args.putString("someTitle", title)
        fragmentFirst.arguments = args
        return fragmentFirst
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = requireArguments().getInt("someInt", 0);
        title = requireArguments().getString("someTitle");
    }
    lateinit var binding : FragmentIntroDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroDataBinding.inflate(inflater, container, false)

        if (page == 1){

            binding.imageSplash.setImageResource(R.drawable.d1)
        }
        if (page == 2){

            binding.imageSplash.setImageResource(R.drawable.d2)
        }
        if (page == 3){

            binding.imageSplash.setImageResource(R.drawable.d3)
        }
        if (page == 4){

            binding.imageSplash.setImageResource(R.drawable.deliveryman)
        }

        return binding.root
    }
}