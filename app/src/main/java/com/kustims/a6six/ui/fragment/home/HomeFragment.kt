package com.kustims.a6six.ui.fragment.home

import com.kustims.a6six.ui.fragment.home.RestaurantRecommendationFragment
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.data.model.response.DetailPlaceResponse
import com.kustims.a6six.data.network.APIS
import com.kustims.a6six.data.network.RetrofitClient
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.FragmentHomeBinding
import com.kustims.a6six.ui.adapter.HomeRecyclerViewAdapter
import com.kustims.a6six.ui.adapter.ViewPager2Adapter
import com.kustims.a6six.ui.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import timber.log.Timber


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var retService: APIS
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    //viewPager2 이미지 초기화
    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.ic_banner_example)
        add(R.drawable.ic_banner_example)
        add(R.drawable.ic_banner_example)
    }

    private lateinit var viewPager: ViewPager2
    private lateinit var indicators: Array<ImageView?>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurantRecommendationFragment = RestaurantRecommendationFragment()
        val cafeRecommendationFragment = CafeRecommendationFragment()
        val playRecommendationFragment = PlayRecommendationFragment()
        val placeDetailFragment = PlaceDetailFragment()

        //accessToken
        val preferenceManager = PreferenceManager(requireContext())
        // Store a string value
        preferenceManager.setString(PreferenceManager.ACCESS_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0bHNhbHN0ajAxQGR1a3N1bmcuYWMua3IiLCJhdXRoIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0OTEwMDMyLCJleHAiOjE2ODg1MTAwMzJ9.klAMhLWSUQL-43lzS0i4vbWI-slpPkixz6hUxG1n4Tx1xj9Kl7rDt4Ee1ccPkj1istfYNUZdWteqD-JELtX_Nw")

        val accessToken = preferenceManager.getString(PreferenceManager.ACCESS_TOKEN).toString()
        Log.d("Home accessToken", accessToken)

        //retrofit
        retService = RetrofitClient
            .getRetrofitInstance()
            .create(APIS::class.java)


        initViewPager2()

        binding.btnGoRestaurant.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, restaurantRecommendationFragment)
                .addToBackStack(null)
                .commit()
        }

        //test용
//        binding.btnGoRestaurant.setOnClickListener {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fcv_main, placeDetailFragment)
//                .addToBackStack(null)
//                .commit()
//        }

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

        // ViewModel 초기화
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeRecyclerViewAdapter = HomeRecyclerViewAdapter { task ->
            //click event 처리
        }

        // RecyclerView 구성
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = homeRecyclerViewAdapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // ViewModel과 RecyclerView 어댑터 연결
        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            homeRecyclerViewAdapter.updateTasks(tasks)
        })



        //이번주 top 2
        lifecycleScope.launch() {
            try {
                val response = retService.getTop2Data()
                if (response.isSuccessful) {
                    val top2Data = response.body()?.data
                    //data 처리
                    Log.d( "홈","${top2Data.toString()}")

                    //restaurant
                    Picasso.get()
                        .load(top2Data!!.restaurant[0].img)
                        .into(binding.likilistRestaurantImage1)

                    Picasso.get()
                        .load(top2Data!!.restaurant[1].img)
                        .into(binding.likilistRestaurantImage2)

                    binding.likilistRestaurantName1.text = top2Data.restaurant[0].name
                    binding.likilistRestaurantName2.text = top2Data.restaurant[1].name

                    //cafe
                    Picasso.get()
                        .load(top2Data!!.cafe[0].img)
                        .into(binding.likilistCafeImage1)

                    Picasso.get()
                        .load(top2Data!!.cafe[1].img)
                        .into(binding.likilistCafeImage2)

                    binding.likilistCafeName1.text = top2Data.cafe[0].name
                    binding.likilistCafeName2.text = top2Data.cafe[1].name

                    //play
                    Picasso.get()
                        .load(top2Data.play[0].img)
                        .into(binding.likilistPlayImage1)

                    Picasso.get()
                        .load(top2Data.play[1].img)
                        .into(binding.likilistPlayImage2)

                    binding.likilistPlayName1.text = top2Data.play[0].name
                    binding.likilistPlayName2.text = top2Data.play[1].name


                } else {
                    // 에러 처리
                    Timber.d("Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                // 예외 처리
                Timber.e(/* message = */ "Exception: ${e.message}")
            }
        }

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
