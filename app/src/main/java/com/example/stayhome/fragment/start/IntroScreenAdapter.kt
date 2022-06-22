package com.example.stayhome.fragment.start

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class IntroScreenAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val fragmentTitle: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> IntroDataFragment().newInstance(1, "Page # 1")!!
            1 -> IntroDataFragment().newInstance(2, "Page # 2")!!
            2 -> IntroDataFragment().newInstance(3, "Page # 3")!!
            3 -> IntroDataFragment().newInstance(4, "Page # 4")!!
            else -> IntroDataFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }
}
