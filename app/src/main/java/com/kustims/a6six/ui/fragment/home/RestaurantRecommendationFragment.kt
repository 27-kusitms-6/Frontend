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
    private val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0bHNhbHN0ajAxQGR1a3N1bmcuYWMua3IiLCJhdXRoIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0OTEwMDMyLCJleHAiOjE2ODg1MTAwMzJ9.klAMhLWSUQL-43lzS0i4vbWI-slpPkixz6hUxG1n4Tx1xj9Kl7rDt4Ee1ccPkj1istfYNUZdWteqD-JELtX_Nw"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view 초기화
//        binding.region.setImageResource(R.drawable.ic_filter_1)
//        binding.popularity.setImageResource(R.drawable.ic_filter_2)
//        binding.preference.setImageResource(R.drawable.ic_filter_3)
//        binding.type.setImageResource(R.drawable.ic_filter_4)

        val defaultFilters = "조용한,트렌디한" //사용자 기본 선택 필터로 변경 필요
        var category2 = "null"
        var filters: List<String> = defaultFilters.split(",")
        var orderBy : Int = 1
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


        // ViewModel 초기화
        val factory = RecommendationViewModelFactory(requireActivity().application, category2, filters, orderBy, accessToken)
        Log.d("RestaurantRecommendationFragment", "category2: $category2, filters: $filters, orderBy: $orderBy, accessToken: $accessToken")
        viewModel = ViewModelProvider(this, factory).get(RecommendationViewModel::class.java)

        recommendationViewAdapter = RecommendationViewAdapter { place ->
            // Click event 처리
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

        // Fetch recommendation places
        viewModel.fetchPlaces(category2, filters, orderBy)

        var regionFragment: RegionFilterFragment? = null
        var popularityFragment: PopularFilterFragment? = null
        var preferencesFragment: PreferencesFilterFragment? = null
        var typeFragment: TypeFilterRestaurantFragment? = null

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
                typeFragment = TypeFilterRestaurantFragment()
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
