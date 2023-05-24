package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentPopularFilterBinding
import com.kustims.a6six.databinding.FragmentRegionFilterBinding

class PopularFilterFragment : BaseFragment<FragmentPopularFilterBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPopularFilterBinding {
        return FragmentPopularFilterBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.popularOption1.setOnClickListener {
            binding.popularOption1.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption1.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.popularOption2.setOnClickListener {
            binding.popularOption2.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption2.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }
        binding.popularOption3.setOnClickListener {
            binding.popularOption3.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption3.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }
        binding.popularOption4.setOnClickListener {
            binding.popularOption4.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption4.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }
        binding.popularOption5.setOnClickListener {
            binding.popularOption5.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption5.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }



    }
}