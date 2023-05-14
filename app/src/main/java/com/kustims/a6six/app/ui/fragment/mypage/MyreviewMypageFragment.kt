package com.kustims.a6six.app.ui.fragment.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.app.ui.fragment.BaseFragment
import com.kustims.a6six.databinding.FragmentMyreviewMypageBinding


class MyreviewMypageFragment : BaseFragment<FragmentMyreviewMypageBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyreviewMypageBinding {
        return FragmentMyreviewMypageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}