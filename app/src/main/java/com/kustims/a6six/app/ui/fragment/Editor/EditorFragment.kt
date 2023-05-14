package com.kustims.a6six.app.ui.fragment.Editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.app.ui.fragment.BaseFragment
import com.kustims.a6six.databinding.FragmentEditorBinding

class EditorFragment: BaseFragment<FragmentEditorBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditorBinding {
        return FragmentEditorBinding.inflate(inflater,  container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}