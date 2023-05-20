package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentHomeBinding
import com.kustims.a6six.ui.adapter.MypagePagerFragmentStateAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var mypagetabAdapter : MypagePagerFragmentStateAdapter
    private lateinit var viewPager: ViewPager2

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val tabLayout = binding.
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            if(position == 0) {
//                tab.text = "LIKLIST"
//            }
//            else if (position == 1) {
//                tab.text = "나의 리뷰"
//            }
//        }.attach()

        //adapter attach
//        mypagetabAdapter = MypageTabAdapter(this)
//        viewPager.adapter = mypagetabAdapter

    }
}