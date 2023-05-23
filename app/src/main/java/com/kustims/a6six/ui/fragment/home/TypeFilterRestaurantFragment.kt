package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentTypeFilterRestaurantBinding

class TypeFilterRestaurantFragment : BaseFragment<FragmentTypeFilterRestaurantBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTypeFilterRestaurantBinding {
        return FragmentTypeFilterRestaurantBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}