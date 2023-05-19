package com.kustims.a6six.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kustims.a6six.ui.fragment.mypage.ViewpagerMypage2Fragment
import com.kustims.a6six.ui.fragment.mypage.ViewpagerMypageFragment

class MypageTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ViewpagerMypage2Fragment()
            else -> {
                ViewpagerMypageFragment()
            }
        }
    }


}