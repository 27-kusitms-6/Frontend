package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentPreferencesFilterBinding

class PreferencesFilterFragment : BaseFragment<FragmentPreferencesFilterBinding>() {

    private var filter: String = ""
    var set_fragment: Int = 1

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPreferencesFilterBinding {
        return FragmentPreferencesFilterBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val set_fragment_value = arguments?.getInt("set_fragment_value", 1)
        if (set_fragment_value != null) {
            set_fragment = set_fragment_value
        }

        binding.btnClose.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this@PreferencesFilterFragment)
                .commit()
        }

        binding.preferenceOption1.setOnClickListener {
            binding.preferenceOption1.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption1.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "조용한"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }

        binding.preferenceOption2.setOnClickListener {
            binding.preferenceOption2.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption2.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "색다른"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }

        binding.preferenceOption3.setOnClickListener {
            binding.preferenceOption3.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption3.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "전통적인"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }

        binding.preferenceOption4.setOnClickListener {
            binding.preferenceOption4.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption4.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "모던한"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }

        binding.preferenceOption5.setOnClickListener {
            binding.preferenceOption5.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption5.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "화려한"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }

        binding.preferenceOption6.setOnClickListener {
            binding.preferenceOption6.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption6.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "로맨틱한"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }

        binding.preferenceOption7.setOnClickListener {
            binding.preferenceOption7.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption7.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "활기찬"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }

        binding.preferenceOption8.setOnClickListener {
            binding.preferenceOption8.setBackgroundResource(R.drawable.bg_purple_item_select_round)
            binding.preferenceOption8.setTextColor(resources.getColor(R.color.purple_main))
            //fragment replace
            filter = "트렌디한"
            openRestaurantRecommendationFragmentWithDelay(filter, 300)

        }
        }

    private fun openRestaurantRecommendationFragmentWithDelay(filter: String, delayMillis: Long) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            openRestaurantRecommendationFragment(filter)
        }, delayMillis)
    }

    private fun openRestaurantRecommendationFragment(filter: String) {
        val restaurantRecommendationFragment = RestaurantRecommendationFragment()
        val cafeRecommendationFragment = CafeRecommendationFragment()
        val playRecommendationFragment = PlayRecommendationFragment()
        var go_fragment: BaseFragment<*>? = null
        when (set_fragment) {
            1 -> go_fragment = restaurantRecommendationFragment
            2 -> go_fragment = cafeRecommendationFragment
            3 -> go_fragment = playRecommendationFragment
            else -> go_fragment = restaurantRecommendationFragment
        }
        val bundle = Bundle().apply {
            putString("filter", filter)
        }
        go_fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
            .replace(R.id.fcv_main, go_fragment)
            .addToBackStack(null)
            .commit()
    }
}