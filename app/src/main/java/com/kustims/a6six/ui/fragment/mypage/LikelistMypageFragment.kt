package com.kustims.a6six.app.ui.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.app.Base.BaseFragment
import com.kustims.a6six.databinding.FragmentLikelistMypageBinding


class LikelistMypageFragment : BaseFragment<FragmentLikelistMypageBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLikelistMypageBinding {
        return FragmentLikelistMypageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}