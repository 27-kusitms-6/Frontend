package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.FragmentRestaurantRecommendationBinding
import com.kustims.a6six.ui.adapter.RecommendationViewAdapter
import com.kustims.a6six.ui.fragment.home.*
import com.kustims.a6six.ui.viewmodel.RecommendationViewModel
import com.kustims.a6six.ui.viewmodel.RecommendationViewModelFactory

class RestaurantRecommendationFragment : BaseFragment<FragmentRestaurantRecommendationBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRestaurantRecommendationBinding {
        return FragmentRestaurantRecommendationBinding.inflate(inflater, container, false)
    }

    private lateinit var viewModel: RecommendationViewModel
    private lateinit var recommendationViewAdapter: RecommendationViewAdapter
    private var accessToken = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //accessToken
        val preferenceManager = PreferenceManager(requireContext())
        accessToken = preferenceManager.getString(PreferenceManager.ACCESS_TOKEN).toString()

        val defaultFilters = "색다른,활기찬" // 사용자 기본 선택 필터로 변경 필요
        var category2 = "null"
        var filters: List<String> = defaultFilters.split(",")
        var orderBy: Int = 1
        // orderBy 값 전달 받기
        val orderByValue = arguments?.getInt("orderBy", 1)
        if (orderByValue != null) {
            binding.region.setImageResource(R.drawable.ic_filter_1)
            binding.preference.setImageResource(R.drawable.ic_filter_3)
            binding.type.setImageResource(R.drawable.ic_filter_4)
            binding.popularity.setImageResource(R.drawable.ic_filter2_select)
            orderBy = orderByValue
        }
        val filter = arguments?.getString("filter", "")
        if (!filter.isNullOrEmpty()) {
            binding.region.setImageResource(R.drawable.ic_filter_1)
            binding.popularity.setImageResource(R.drawable.ic_filter_2)
            binding.type.setImageResource(R.drawable.ic_filter_4)
            binding.preference.setImageResource(R.drawable.ic_filter3_select)
            filters = filter.split(",")
        }

        val type = arguments?.getString("type", "")
        if (!type.isNullOrEmpty()) {
            binding.region.setImageResource(R.drawable.ic_filter_1)
            binding.popularity.setImageResource(R.drawable.ic_filter_2)
            binding.preference.setImageResource(R.drawable.ic_filter_3)
            binding.type.setImageResource(R.drawable.ic_filter4_select)
            category2 = type
        }

        // recycler view
        // ViewModel 초기화
        val factory = RecommendationViewModelFactory(
            requireActivity().application,
            category2,
            filters,
            orderBy,
            accessToken,
        )
        viewModel = ViewModelProvider(this, factory).get(RecommendationViewModel::class.java)

        recommendationViewAdapter = RecommendationViewAdapter { place ->
            // Click event 처리
            var bundle = Bundle()
            var placeId = place.id
            openPlaceDetailFragment(placeId)
        }

        // RecyclerView 구성
        binding.recyclerview.adapter = recommendationViewAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel과 RecyclerView 어댑터 연결
        viewModel.places.observe(viewLifecycleOwner, Observer { places ->
            places?.let {
                recommendationViewAdapter.updatePlaces(it)
            }
        })

        viewModel.fetchPlaces(category2, filters, orderBy)

        var regionFragment: RegionFilterFragment? = null
        var popularityFragment: PopularFilterFragment? = null
        var preferencesFragment: PreferencesFilterFragment? = null
        var typeFragment: TypeFilterRestaurantFragment? = null

        binding.region.setOnClickListener {
            if (regionFragment == null) {
                binding.region.setImageResource(R.drawable.ic_filter1_select)
                val tempFragment = RegionFilterFragment()
                regionFragment = tempFragment

                val bundle = Bundle().apply {
                    putInt("set_fragment_value", 1)
                }
                tempFragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.region_fragment, tempFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.region.setImageResource(R.drawable.ic_filter_1)
                parentFragmentManager.beginTransaction()
                    .remove(regionFragment!!)
                    .commit()
                regionFragment = null
            }
        }

        binding.popularity.setOnClickListener {
            if (popularityFragment == null) {
                binding.popularity.setImageResource(R.drawable.ic_filter2_select)
                popularityFragment = PopularFilterFragment()
                val bundle = Bundle().apply {
                    putInt("set_fragment_value", 1)
                }
                popularityFragment?.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.popular_fragment, popularityFragment!!)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.popularity.setImageResource(R.drawable.ic_filter_2)
                parentFragmentManager.beginTransaction()
                    .remove(popularityFragment!!)
                    .commit()
                popularityFragment = null
            }
        }

        binding.preference.setOnClickListener {
            if (preferencesFragment == null) {
                binding.preference.setImageResource(R.drawable.ic_filter3_select)
                preferencesFragment = PreferencesFilterFragment()
                val bundle = Bundle().apply {
                    putInt("set_fragment_value", 1)
                }
                preferencesFragment?.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.preferences_fragment, preferencesFragment!!)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.preference.setImageResource(R.drawable.ic_filter_3)
                parentFragmentManager.beginTransaction()
                    .remove(preferencesFragment!!)
                    .commit()
                preferencesFragment = null
            }
        }

        binding.type.setOnClickListener {
            if (typeFragment == null) {
                binding.type.setImageResource(R.drawable.ic_filter4_select)
                typeFragment = TypeFilterRestaurantFragment()
                val bundle = Bundle().apply {
                    putInt("set_fragment_value", 1)
                }
                typeFragment?.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.type_fragment, typeFragment!!)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.type.setImageResource(R.drawable.ic_filter_4)
                parentFragmentManager.beginTransaction()
                    .remove(typeFragment!!)
                    .commit()
                typeFragment = null
            }
        }
    }

    private fun openPlaceDetailFragment(placeId: Int) {
        val placeDetailFragment = PlaceDetailFragment()
        val bundle = Bundle().apply {
            putInt("placeId", placeId)
        }
        placeDetailFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.fcv_main, placeDetailFragment)
            .addToBackStack(null)
            .commit()
    }
}

