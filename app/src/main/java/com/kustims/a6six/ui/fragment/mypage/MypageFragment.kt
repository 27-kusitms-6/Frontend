package com.kustims.a6six.ui.fragment.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentMypageBinding
import com.kustims.a6six.ui.adapter.MypagePagerFragmentStateAdapter


class MypageFragment : BaseFragment<FragmentMypageBinding>() {


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMypageBinding {
        return FragmentMypageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = MypagePagerFragmentStateAdapter(this)
            .apply {
                addFragment(MyreviewMypageFragment())
                addFragment(LikelistMypageFragment())
            }

        val viewPager: ViewPager2 = binding.viewPagerMypage.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d("ViewPagerFragment" , "Page ${position+1}")
                }
            })
        }

        TabLayoutMediator(binding.tabLayoutMypage, viewPager) { tab, position ->
            tab.text = "${position}"
        }.attach()


        binding.settingTopbarMypage.setOnClickListener {
            findNavController().navigate(
                MypageFragmentDirections.actionFragmentMypageToFragmentSettingsMypage()
            )

        }
    }
}


