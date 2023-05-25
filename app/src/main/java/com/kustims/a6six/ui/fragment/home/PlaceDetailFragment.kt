package com.kustims.a6six.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.FragmentPlaceDetailBinding
import com.kustims.a6six.ui.adapter.PlaceDetailViewAdapter
import com.kustims.a6six.ui.viewmodel.PlaceDetailViewModel
import com.kustims.a6six.ui.viewmodel.PlaceDetailViewModelFactory
import com.squareup.picasso.Picasso


class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>() {

    private lateinit var viewModel: PlaceDetailViewModel
    private lateinit var recommendationViewAdapter: PlaceDetailViewAdapter
//    private val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0bHNhbHN0ajAxQGR1a3N1bmcuYWMua3IiLCJhdXRoIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0OTEwMDMyLCJleHAiOjE2ODg1MTAwMzJ9.klAMhLWSUQL-43lzS0i4vbWI-slpPkixz6hUxG1n4Tx1xj9Kl7rDt4Ee1ccPkj1istfYNUZdWteqD-JELtX_Nw"
    var placeId : Int = 28
    private var accessToken = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaceDetailBinding {
        return FragmentPlaceDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //accessToken
        val preferenceManager = PreferenceManager(requireContext())
        accessToken = preferenceManager.getString(PreferenceManager.ACCESS_TOKEN).toString()

        val placeIdValue = arguments?.getInt("placeId", 28)
        if (placeIdValue != null) {
            placeId = placeIdValue
            Log.d("placeId", placeId.toString())
        }

        binding.btnGoReview.setOnClickListener{
            val reviewFragment = ReviewFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, reviewFragment)
                .addToBackStack(null)
                .commit()
        }

        // ViewModel 초기화
        val factory = PlaceDetailViewModelFactory(requireActivity().application, placeId, accessToken)

        viewModel = ViewModelProvider(this, factory).get(PlaceDetailViewModel::class.java)

        viewModel.place.observe(viewLifecycleOwner, Observer { place ->
            // UI 업데이트
            binding.tvPlaceName.text = place.name
            binding.tvPlaceRating.text = place.starRating?.toString() ?: ""
            binding.textView2.text = "${place.reviewCount ?: 0} 개"
            binding.tvPlaceAddress.text = place.content ?: ""
            binding.positiveStickerName1.text = place.top2PositiveStickerName?.getOrNull(0) ?: ""
            binding.positiveStickerName2.text = place.top2PositiveStickerName?.getOrNull(1) ?: ""
            binding.negativeStickerName1.text = place.top2NegativeStickerName?.getOrNull(0) ?: ""
            binding.negativeStickerName2.text = place.top2NegativeStickerName?.getOrNull(1) ?: ""
            binding.negativeStickerCount1.text = place.top2NegativeStickerCount?.getOrNull(0)?.toString() ?: ""
            binding.negativeStickerCount2.text = place.top2NegativeStickerCount?.getOrNull(1)?.toString() ?: ""
            binding.positiveStickerCount1.text = place.top2PositiveStickerCount?.getOrNull(0)?.toString() ?: ""
            binding.positiveStickerCount2.text = place.top2PositiveStickerCount?.getOrNull(1)?.toString() ?: ""


            var star = place.starRating.toInt()
            //별점
            when (star) {
                1 -> binding.starInt.setImageResource(R.drawable.ic_star1)
                2 -> binding.starInt.setImageResource(R.drawable.ic_star2)
                3 -> binding.starInt.setImageResource(R.drawable.ic_star3)
                4 -> binding.starInt.setImageResource(R.drawable.ic_star4)
                5 -> binding.starInt.setImageResource(R.drawable.ic_star5)
                else -> binding.starInt.setImageResource(R.drawable.ic_star3)
            }


            if (place.placeImg != null) {
                Picasso.get()
                    .load(place.placeImg)
                    .into(binding.tvPlaceImage)
            } else {
                // null
            }

            if (place.top2NegativeStickers.size == 2) {
                Picasso.get()
                    .load(place.top2NegativeStickers[0])
                    .into(binding.negativeStickerImage1)

                Picasso.get()
                    .load(place.top2NegativeStickers[1])
                    .into(binding.negativeStickerImage2)
            } else {
                // null
            }

            if (place.top2PositiveStickers.size == 2) {
                Picasso.get()
                    .load(place.top2PositiveStickers[0])
                    .into(binding.positiveStickerImage1)

                Picasso.get()
                    .load(place.top2PositiveStickers[1])
                    .into(binding.positiveStickerImage2)
            } else {
                // null
            }

        })

        recommendationViewAdapter = PlaceDetailViewAdapter { place ->
            // Click event 처리

        }

        // RecyclerView 구성
        binding.recyclerview.adapter = recommendationViewAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel과 RecyclerView 어댑터 연결
        viewModel.reviews.observe(viewLifecycleOwner, Observer { places ->
            places?.let {
                recommendationViewAdapter.updateReviews(it)
            }
        })

        // Fetch recommendation places
        viewModel.fetchReview()

    }
}