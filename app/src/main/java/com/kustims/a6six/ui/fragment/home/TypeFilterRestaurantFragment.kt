package com.kustims.a6six.ui.fragment.home

import RestaurantRecommendationFragment
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentTypeFilterRestaurantBinding

class TypeFilterRestaurantFragment : BaseFragment<FragmentTypeFilterRestaurantBinding>() {

    private var type: String = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTypeFilterRestaurantBinding {
        return FragmentTypeFilterRestaurantBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.icTypeKorean.setOnClickListener {
            binding.icTypeKorean.setImageResource(R.drawable.ic_type_restaurant_filter_korean_select)
            //fragment replace
            type = "한식"
            openRestaurantRecommendationFragmentWithDelay(type, 300)

        }

        binding.icTypeChinese.setOnClickListener {
            binding.icTypeChinese.setImageResource(R.drawable.ic_type_restaurant_filter_chinese_select)
            //fragment replace
            type = "중식"
            openRestaurantRecommendationFragmentWithDelay(type, 300)

        }

        binding.icTypeJapanese.setOnClickListener {
            binding.icTypeJapanese.setImageResource(R.drawable.ic_type_restaurant_filter_japanese_select)
            //fragment replace
            type = "일식"
            openRestaurantRecommendationFragmentWithDelay(type, 300)

        }

        binding.icTypeWestern.setOnClickListener {
            binding.icTypeWestern.setImageResource(R.drawable.ic_type_restaurant_filter_western_select)
            //fragment replace
            type = "양식"
            openRestaurantRecommendationFragmentWithDelay(type, 300)

        }

        binding.icTypeBar.setOnClickListener {
            binding.icTypeBar.setImageResource(R.drawable.ic_type_restaurant_filter_bar_select)
            //fragment replace
            type = "술집"
            openRestaurantRecommendationFragmentWithDelay(type, 300)

        }

        binding.icTypeBrunch.setOnClickListener {
            binding.icTypeBrunch.setImageResource(R.drawable.ic_type_restaurant_filter_etc_select)
            //fragment replace
            type = "브런치"
            openRestaurantRecommendationFragmentWithDelay(type, 300)

        }

        binding.icTypeEtc.setOnClickListener {
            binding.icTypeEtc.setImageResource(R.drawable.ic_type_restaurant_filter_etc_select)
            //fragment replace
            type = "기타"
            openRestaurantRecommendationFragmentWithDelay(type, 300)

        }
    }

    private fun openRestaurantRecommendationFragmentWithDelay(type: String, delayMillis: Long) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            openRestaurantRecommendationFragment(type)
        }, delayMillis)
    }

    private fun openRestaurantRecommendationFragment(type: String) {
        val restaurantRecommendationFragment = RestaurantRecommendationFragment()
        val bundle = Bundle().apply {
            putString("type", type)
        }
        restaurantRecommendationFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
            .replace(R.id.fcv_main, restaurantRecommendationFragment)
            .addToBackStack(null)
            .commit()
    }
}