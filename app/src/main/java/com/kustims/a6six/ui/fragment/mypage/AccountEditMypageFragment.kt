package com.kustims.a6six.ui.fragment.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kustims.a6six.base.BaseFragment2
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.FragmentAccountEditMypageBinding
import com.kustims.a6six.ui.activity.MainActivity
import com.kustims.a6six.ui.viewmodel.MypageViewModel
import com.squareup.picasso.Picasso


class AccountEditMypageFragment : BaseFragment2<MypageViewModel, FragmentAccountEditMypageBinding>() {

    override val viewModel: MypageViewModel = MypageViewModel()
    override fun getViewBinding(): FragmentAccountEditMypageBinding = FragmentAccountEditMypageBinding.inflate(layoutInflater)

    lateinit var accessToken: String

    private lateinit var pm: PreferenceManager

    //getUserInfo
    private  lateinit var _name:String
    private lateinit var _email: String
    private lateinit var _phoneNum: String
    private lateinit var _nickname: String
    private lateinit var _imgUrl: String
    private lateinit var _birthDate: String

    //MainActivity 연결 후 pm 생성
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pm = PreferenceManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessToken = (activity as? MainActivity)?.accessToken ?: ""
        Log.d("accessToken_Mypage", accessToken)

        initViews()

        _imgUrl = pm.getImgUrl().toString()
        Picasso.get()
            .load(_imgUrl)
            .into(binding.ivProfileEdit)

        _name = pm.getName().toString()
        binding.editTextNameMypage.setText(_name)

        _nickname = pm.getNickname().toString()
        binding.editTextNicknameMypage.setText(_nickname)

        _email = pm.getEmail().toString()
        Log.d("Accont_Edit", "_email"+ _email)
        binding.editTextEmailMypage.setText(_email)

        _phoneNum = pm.getPhoneNum().toString()
        Log.d("Accont_Edit", "_phoneNum"+ _phoneNum)
        binding.editTextNumberMypage.setText(_phoneNum)

        _birthDate = pm.getbirthDate().toString()
        binding.editTextBirthMypage.setText(_birthDate)

    }




}