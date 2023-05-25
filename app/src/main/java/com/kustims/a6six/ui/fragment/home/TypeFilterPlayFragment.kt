package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentTypeFilterPlayBinding

class TypeFilterPlayFragment : BaseFragment<FragmentTypeFilterPlayBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTypeFilterPlayBinding {
        return FragmentTypeFilterPlayBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this@TypeFilterPlayFragment)
                .commit()
        }

    }
}