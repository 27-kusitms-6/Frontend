package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentPreferencesFilterBinding

class PreferencesFilterFragment : BaseFragment<FragmentPreferencesFilterBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPreferencesFilterBinding {
        return FragmentPreferencesFilterBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.preferenceOption1.setOnClickListener {
            binding.preferenceOption1.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption1.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.preferenceOption2.setOnClickListener {
            binding.preferenceOption2.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption2.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.preferenceOption3.setOnClickListener {
            binding.preferenceOption3.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption3.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.preferenceOption4.setOnClickListener {
            binding.preferenceOption4.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption4.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.preferenceOption5.setOnClickListener {
            binding.preferenceOption5.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption5.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.preferenceOption6.setOnClickListener {
            binding.preferenceOption6.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption6.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.preferenceOption7.setOnClickListener {
            binding.preferenceOption7.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption7.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }

        binding.preferenceOption8.setOnClickListener {
            binding.preferenceOption8.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption8.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace

        }



    }
}