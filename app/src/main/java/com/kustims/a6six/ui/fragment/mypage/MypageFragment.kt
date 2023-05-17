package com.kustims.a6six.ui.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD:app/src/main/java/com/kustims/a6six/app/ui/fragment/mypage/MypageFragment.kt
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kustims.a6six.app.ui.fragment.BaseFragment
import com.kustims.a6six.app.viewmodel.MypageViewModel
=======
import com.kustims.a6six.base.BaseFragment
>>>>>>> 6d67c2b2df8ca98c5e969b80f866db10b25e0650:app/src/main/java/com/kustims/a6six/ui/fragment/mypage/MypageFragment.kt
import com.kustims.a6six.databinding.FragmentMypageBinding


class MypageFragment : BaseFragment<FragmentMypageBinding>() {

    private val viewModel: MypageViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMypageBinding {
        return FragmentMypageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        binding.settingTopbarMypage.setOnClickListener {
            findNavController().navigate(
                MypageFragmentDirections.actionFragmentMypageToFragmentSettingsMypage()
            )

        }
    }
}


