package com.kustims.a6six.ui.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentViewpagerMypageBinding

class ViewpagerMypageFragment : BaseFragment<FragmentViewpagerMypageBinding>() {

    private lateinit var recyclerView: RecyclerView

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewpagerMypageBinding {
        return FragmentViewpagerMypageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    }