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
import com.kustims.a6six.databinding.FragmentPlaceDetailBinding
import com.kustims.a6six.ui.adapter.PlaceDetailViewAdapter
import com.kustims.a6six.ui.viewmodel.PlaceDetailViewModel
import com.kustims.a6six.ui.viewmodel.PlaceDetailViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_place_detail.*


class PlaceDetailFragment : BaseFragment<FragmentPlaceDetailBinding>() {

    private lateinit var viewModel: PlaceDetailViewModel
    private lateinit var recommendationViewAdapter: PlaceDetailViewAdapter
    private val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0bHNhbHN0ajAxQGR1a3N1bmcuYWMua3IiLCJhdXRoIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0OTEwMDMyLCJleHAiOjE2ODg1MTAwMzJ9.klAMhLWSUQL-43lzS0i4vbWI-slpPkixz6hUxG1n4Tx1xj9Kl7rDt4Ee1ccPkj1istfYNUZdWteqD-JELtX_Nw"
    var placeId : Int = 28

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaceDetailBinding {
        return FragmentPlaceDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel 초기화
        val factory = PlaceDetailViewModelFactory(requireActivity().application, placeId, accessToken)

        viewModel = ViewModelProvider(this, factory).get(PlaceDetailViewModel::class.java)

        viewModel.place.observe(viewLifecycleOwner, Observer { place ->
            // UI 업데이트
            binding.tvPlaceName.text = place.name
            binding.tvPlaceRating.text = place.starRating.toString()
            binding.textView2.text = "${place.reviewCount} 개"
            binding.tvPlaceAddress.text = place.content
            binding.negativeStickerCount1.text = place.top2NegativeStickerCount[0].toString()
            binding.negativeStickerCount2.text = place.top2NegativeStickerCount[1].toString()
            binding.positiveStickerCount1.text = place.top2PositiveStickerCount[0].toString()
            binding.positiveStickerCount2.text = place.top2PositiveStickerCount[1].toString()

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


            //이미지 처리
            Picasso.get()
                .load(place.placeImg)
                .into(binding.tvPlaceImage)

            Picasso.get()
                .load(place.top2NegativeStickers[0])
                .into(binding.negativeStickerImage1)

            Picasso.get()
                .load(place.top2NegativeStickers[1])
                .into(binding.negativeStickerImage2)

            Picasso.get()
                .load(place.top2PositiveStickers[0])
                .into(binding.positiveStickerImage1)

            Picasso.get()
                .load(place.top2PositiveStickers[1])
                .into(binding.positiveStickerImage2)

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