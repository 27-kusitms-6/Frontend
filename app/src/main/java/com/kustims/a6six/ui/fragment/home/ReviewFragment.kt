package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentReviewBinding
import com.kustims.a6six.databinding.FragmentSeoulFilterBinding

class ReviewFragment : BaseFragment<FragmentReviewBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReviewBinding {
        return FragmentReviewBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "추후 업데이트될 에정입니다.", Toast.LENGTH_SHORT).show()


    }
}