package com.kustims.a6six.app.ui.fragment.Mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.app.ui.fragment.BaseFragment
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
    }
}


