package com.kustims.a6six.ui.fragment.Editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.tabs.TabLayoutMediator
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentEditorBinding

class EditorFragment: BaseFragment<FragmentEditorBinding>() {

    private val mPageNumbers = 3
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditorBinding {
        return FragmentEditorBinding.inflate(inflater,  container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSlider = binding.imageSliderEditor
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("first_image"))
        imageList.add(SlideModel("second_image"))
        imageList.add(SlideModel("third_image"))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)

    }


}