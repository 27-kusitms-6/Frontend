package com.kustims.a6six.ui.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentAccountEditMypageBinding


class AccountEditMypageFragment : BaseFragment<FragmentAccountEditMypageBinding>() {


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccountEditMypageBinding {
        return FragmentAccountEditMypageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //tab layout attach


    }






}