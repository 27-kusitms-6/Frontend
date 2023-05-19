package com.kustims.a6six.ui.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentMypageBinding


class MypageFragment : BaseFragment<FragmentMypageBinding>() {


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMypageBinding {
        return FragmentMypageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.settingTopbarMypage.setOnClickListener {
            findNavController().navigate(
                MypageFragmentDirections.actionFragmentMypageToFragmentSettingsMypage()
            )

        }
    }
}


