package com.example.stayhome.fragment.start

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.stayhome.R
import com.example.stayhome.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {
    lateinit var viewPagerAdapter: IntroScreenAdapter
    lateinit var splashBinding: FragmentIntroBinding
    var t = "Splash Fragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        splashBinding = FragmentIntroBinding.inflate(inflater, container, false)

        val support = requireActivity().supportFragmentManager.beginTransaction()
        viewPagerAdapter = IntroScreenAdapter(requireActivity().supportFragmentManager)
        splashBinding.splashViewPager.adapter = viewPagerAdapter
        splashBinding.indicatorDots.setViewPager(splashBinding.splashViewPager)

        splashBinding.splashViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.e(t, "onPageScrolled")

                if (position == 3) {
                    splashBinding.btnGetStarted.setOnClickListener {

                        val fragment = SignUpFragment()
                        support.replace(R.id.mainActivityFragmentContainer,fragment).commit()

                        Log.e("ttt1","hello")
                    }
                }
                else{
                    splashBinding.btnGetStarted.setOnClickListener {
                        splashBinding.splashViewPager.currentItem = splashBinding.splashViewPager.currentItem + 1
                    }
                }
            }
            override fun onPageScrollStateChanged(state: Int) {
                Log.e(t, "onPageScrollStateChanged")
            }
        })

        splashBinding.btnSkip.setOnClickListener {
//            Navigation.findNavController(splashBinding.root).navigate(R.id.action_welcomeFragment_to_RVFragment)
            val fragment = SignUpFragment()
            support.replace(R.id.mainActivityFragmentContainer,fragment).commit()
        }

        return splashBinding.root
    }

}