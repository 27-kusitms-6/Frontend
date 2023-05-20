package com.kustims.a6six.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kustims.a6six.ui.fragment.mypage.ViewpagerMypage2Fragment
import com.kustims.a6six.ui.fragment.mypage.ViewpagerMypageFragment

class MypagePagerFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var fragments : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ViewpagerMypage2Fragment()
            else -> {
                ViewpagerMypageFragment()
            }
        }
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyItemInserted(fragments.size -1)
    }

    fun removeFragment() {
        fragments.removeAt(fragments.size - 1)
        notifyItemRemoved(fragments.size -1)
    }


}