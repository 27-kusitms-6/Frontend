package com.kustims.a6six.ui.fragment.home

import com.kustims.a6six.ui.fragment.home.RestaurantRecommendationFragment
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentPopularFilterBinding
import com.kustims.a6six.databinding.FragmentRegionFilterBinding

class PopularFilterFragment : BaseFragment<FragmentPopularFilterBinding>() {

    private var orderBy: Int = 0
//    private val restaurantRecommendationFragment = RestaurantRecommendationFragment()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPopularFilterBinding {
        return FragmentPopularFilterBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this@PopularFilterFragment)
                .commit()
        }

        binding.popularOption1.setOnClickListener {
            binding.popularOption1.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption1.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            orderBy = 1 // 원하는 orderBy 값
            openRestaurantRecommendationFragmentWithDelay(orderBy, 300)

        }

        binding.popularOption2.setOnClickListener {
            binding.popularOption2.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption2.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            orderBy = 2 // 원하는 orderBy 값
            openRestaurantRecommendationFragmentWithDelay(orderBy, 400)

        }
        binding.popularOption3.setOnClickListener {
            binding.popularOption3.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption3.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            orderBy = 3 // 원하는 orderBy 값
            openRestaurantRecommendationFragmentWithDelay(orderBy, 400)

        }
        binding.popularOption4.setOnClickListener {
            binding.popularOption4.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption4.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            orderBy = 4 // 원하는 orderBy 값
            openRestaurantRecommendationFragmentWithDelay(orderBy, 400)

        }
        binding.popularOption5.setOnClickListener {
            binding.popularOption5.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.popularOption5.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            orderBy = 5 // 원하는 orderBy 값
            openRestaurantRecommendationFragmentWithDelay(orderBy, 400)

        }
    }

    private fun openRestaurantRecommendationFragmentWithDelay(orderBy: Int, delayMillis: Long) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            openRestaurantRecommendationFragment(orderBy)
        }, delayMillis)
    }

    private fun openRestaurantRecommendationFragment(orderBy: Int) {
        val restaurantRecommendationFragment = RestaurantRecommendationFragment()
        val bundle = Bundle().apply {
            putInt("orderBy", orderBy)
        }
        restaurantRecommendationFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
            .replace(R.id.fcv_main, restaurantRecommendationFragment)
            .addToBackStack(null)
            .commit()
    }
}