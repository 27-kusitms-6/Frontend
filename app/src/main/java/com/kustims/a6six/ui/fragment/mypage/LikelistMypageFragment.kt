package com.kustims.a6six.ui.fragment.mypage

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentLikelistMypageBinding
import com.kustims.a6six.ui.viewmodel.LikelistViewModel
import com.kustims.a6six.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//
class LikelistMypageFragment : BaseFragment<FragmentLikelistMypageBinding>() {

    private lateinit var viewModel : LikelistViewModel

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLikelistMypageBinding {
        return FragmentLikelistMypageBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
//            viewModel = requireActivity().obtainViewModel(LoginViewModel::class.java)
//            vm = viewModel
//            recyclerView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tv1ViewpagerMypage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, SaveDetailFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.tv2ViewpagerMypage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, ViewpagerMypage2Fragment())
                .addToBackStack(null)
                .commit()
        }
    }




}