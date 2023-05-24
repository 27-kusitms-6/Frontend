package com.kustims.a6six.ui.fragment.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kustims.a6six.base.BaseFragment2
import com.kustims.a6six.databinding.FragmentAccountEditMypageBinding
import com.kustims.a6six.ui.activity.MainActivity
import com.kustims.a6six.ui.viewmodel.MypageViewModel


class AccountEditMypageFragment : BaseFragment2<MypageViewModel, FragmentAccountEditMypageBinding>() {

    override val viewModel: MypageViewModel = MypageViewModel()
    override fun getViewBinding(): FragmentAccountEditMypageBinding = FragmentAccountEditMypageBinding.inflate(layoutInflater)


    lateinit var accessToken: String

    //getUserInfo
    lateinit var nickname: String
    lateinit var filters: List<String>
    lateinit var imgUrl: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessToken = (activity as? MainActivity)?.accessToken ?: ""
        Log.d("accessToken_Mypage", accessToken)

        initViews()


    }


}