package com.kustims.a6six.ui.fragment.Editor

import EditorDetail1Fragment
import EditorDetail2Fragment
import EditorDetail3Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.kustims.a6six.R
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

        imageList.add(SlideModel("https://ifh.cc/g/rwdCjL.jpg"))
        imageList.add(SlideModel("https://ifh.cc/g/CzOf04.jpg"))
        imageList.add(SlideModel("https://ifh.cc/g/CzOf04.jpg"))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)


        binding.iv1ContentEditor.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, EditorDetail1Fragment())
                .addToBackStack(null)
                .commit()
        }

        binding.iv2ContentEditor.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, EditorDetail2Fragment())
                .addToBackStack(null)
                .commit()
        }

        binding.iv3ContentEditor.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, EditorDetail3Fragment())
                .addToBackStack(null)
                .commit()
        }
    }


}