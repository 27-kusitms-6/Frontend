package com.kustims.a6six.ui.fragment.home

import RestaurantRecommendationFragment
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentHomeBinding
import com.kustims.a6six.ui.adapter.ViewPager2Adapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.ic_banner_example)
        add(R.drawable.ic_banner_example)
        add(R.drawable.ic_banner_example)
    }

    private lateinit var viewPager: ViewPager2
    private lateinit var indicators: Array<ImageView?>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager2()

        val restaurantRecommendationFragment = RestaurantRecommendationFragment()
        val cafeRecommendationFragment = CafeRecommendationFragment()
        val playRecommendationFragment = PlayRecommendationFragment()

        binding.btnGoRestaurant.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, restaurantRecommendationFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.btnGoCafe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, cafeRecommendationFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.btnGoPlay.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, playRecommendationFragment)
                .addToBackStack(null)
                .commit()
        }

        val spannable = SpannableStringBuilder(getString(R.string.home_recommendation_week))
        val purpleColor = ContextCompat.getColor(requireContext(), R.color.purple_main)
        val foregroundColorSpan = ForegroundColorSpan(purpleColor)
        spannable.setSpan(
            foregroundColorSpan,
            7,
            15,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvLikilistTitle.text = spannable
    }

    private fun initViewPager2() {
        viewPager = binding.viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            adapter = ViewPager2Adapter(this@HomeFragment, imageList)
        }

        bindViewPager2Event()
        setupIndicators(imageList.size)
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
            }
        })
    }

    private fun bindViewPager2Event() {
        // Handle page selection event
    }

    private fun setupIndicators(count: Int) {
        indicators = arrayOfNulls<ImageView>(count)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(16, 8, 16, 8)
        }

        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext()).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_indicator_inactive
                    )
                )
                layoutParams = params
            }
            binding.layoutIndicators.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(position: Int) {
        for (i in indicators.indices) {
            indicators[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (i == position) {
                        R.drawable.bg_indicator_active
                    } else {
                        R.drawable.bg_indicator_inactive
                    }
                )
            )
        }
    }
}
