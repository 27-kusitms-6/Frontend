package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.databinding.FragmentPlayRecommendationBinding

class PlayRecommendationFragment : BaseFragment<FragmentPlayRecommendationBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayRecommendationBinding {
        return FragmentPlayRecommendationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var regionFragment: RegionFilterFragment? = null
        var popularityFragment: PopularFilterFragment? = null
        var preferencesFragment: PreferencesFilterFragment? = null
        var typeFragment: TypeFilterPlayFragment? = null

        binding.region.setOnClickListener {
            if (regionFragment == null) {
                binding.region.setImageResource(R.drawable.ic_filter1_select)
                regionFragment = RegionFilterFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.region_fragment, regionFragment!!)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.region.setImageResource(R.drawable.ic_filter_1)
                parentFragmentManager.popBackStack()
                regionFragment = null
            }
        }

        binding.popularity.setOnClickListener {
            if (popularityFragment == null) {
                binding.popularity.setImageResource(R.drawable.ic_filter2_select)
                popularityFragment = PopularFilterFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.popular_fragment, popularityFragment!!)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.popularity.setImageResource(R.drawable.ic_filter_2)
                parentFragmentManager.popBackStack()
                popularityFragment = null
            }
        }

        binding.preference.setOnClickListener {
            if (preferencesFragment == null) {
                binding.preference.setImageResource(R.drawable.ic_filter3_select)
                preferencesFragment = PreferencesFilterFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.preferences_fragment, preferencesFragment!!)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.preference.setImageResource(R.drawable.ic_filter_3)
                parentFragmentManager.popBackStack()
                preferencesFragment = null
            }
        }

        binding.type.setOnClickListener {
            if (typeFragment == null) {
                binding.type.setImageResource(R.drawable.ic_filter4_select)
                typeFragment = TypeFilterPlayFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.type_fragment, typeFragment!!)
                    .addToBackStack(null)
                    .commit()
            } else {
                binding.type.setImageResource(R.drawable.ic_filter_4)
                parentFragmentManager.popBackStack()
                typeFragment = null
            }
        }

    }
}