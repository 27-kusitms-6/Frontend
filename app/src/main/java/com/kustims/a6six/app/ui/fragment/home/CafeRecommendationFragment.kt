package com.kustims.a6six.app.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.app.ui.fragment.BaseFragment
import com.kustims.a6six.databinding.FragmentCafeRecommendationBinding


class CafeRecommendationFragment : BaseFragment<FragmentCafeRecommendationBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCafeRecommendationBinding {
        return FragmentCafeRecommendationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}

