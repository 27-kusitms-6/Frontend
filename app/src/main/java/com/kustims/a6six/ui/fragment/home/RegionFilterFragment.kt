package com.kustims.a6six.ui.fragment.home

import RestaurantRecommendationFragment
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentRegionFilterBinding
import kotlinx.android.synthetic.main.fragment_region_filter.*

class RegionFilterFragment : BaseFragment<FragmentRegionFilterBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegionFilterBinding {
        return FragmentRegionFilterBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val imageViewIds = listOf(
            R.id.ic_seoul,
            R.id.ic_incheon,
            R.id.ic_gyeonggido,
            R.id.ic_daejeon,
            R.id.ic_daegu,
            R.id.ic_busan,
            R.id.ic_gangwon,
            R.id.ic_gwangju,
            R.id.ic_jeju,
            R.id.ic_jeonbuk,
            R.id.ic_jeonnam,
            R.id.ic_chungbuk,
            R.id.ic_chungnam,
            R.id.ic_gyeongbuk,
            R.id.ic_gyeongnam,
            R.id.ic_ulsan,
        )

        val originalDrawables = mutableMapOf<Int, Drawable?>()

        imageViewIds.forEach { imageViewId ->
            val imageView = view.findViewById<ImageView>(imageViewId)
            originalDrawables[imageViewId] = imageView.drawable

            imageView.setOnClickListener {
//                Toast.makeText(requireContext(), "Clicked ${resources.getResourceEntryName(imageViewId)}", Toast.LENGTH_SHORT).show()

                val imageName = resources.getResourceEntryName(imageViewId)
                val onImageResourceId = resources.getIdentifier("${imageName}_on", "drawable", requireContext().packageName)
                val onImageDrawable = ContextCompat.getDrawable(requireContext(), onImageResourceId)

                if (imageView.drawable != onImageDrawable) {
                    imageView.setImageDrawable(onImageDrawable)

                    // 다른 ImageView들은 원래 이미지로 초기화
                    imageViewIds.filter { it != imageViewId }.forEach { id ->
                        view.findViewById<ImageView>(id).setImageDrawable(originalDrawables[id])
                    }
                } else {
                    // 이미 선택된 상태이므로 원래 이미지로 변경
                    imageView.setImageDrawable(originalDrawables[imageViewId])
                }

                if (imageName == "ic_seoul") {
                    imageView.postDelayed({
                        val restaurantRecommendationFragment = RestaurantRecommendationFragment()
                        parentFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.fcv_main, restaurantRecommendationFragment)
                            .addToBackStack(null)
                            .commit()
                    }, 300)
                } else {
                    Toast.makeText(requireContext(), "추후 업데이트될 예정입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }




    }
}