package com.kustims.a6six.ui.fragment.mypage


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import androidx.core.content.ContentProviderCompat.requireContext
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment2
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.FragmentPreferMypageBinding
import com.kustims.a6six.ui.activity.MainActivity
import com.kustims.a6six.ui.adapter.GridFilterAdapter
import com.kustims.a6six.ui.viewmodel.MypageViewModel
import com.kustims.a6six.ui.viewmodel.PreferViewModel
import com.kustims.a6six.ui.viewmodelstate.MypageState
import okhttp3.internal.platform.android.AndroidLogHandler.setFilter
import java.nio.file.Files.find

class PreferMypageFragment: BaseFragment2<MypageViewModel, FragmentPreferMypageBinding>() {

    override val viewModel: MypageViewModel = MypageViewModel()
    override fun getViewBinding(): FragmentPreferMypageBinding =
        FragmentPreferMypageBinding.inflate(layoutInflater)

    private lateinit var gridView: GridView
    lateinit var accessToken: String
    lateinit var pm: PreferenceManager

    //pm 검증용
    lateinit var _filters: List<String>
    lateinit var _firtstF: String
    lateinit var _secondF: String

    //보낼 용
    lateinit var filters: List<String>
    private lateinit var message:String


    val filterList = mutableListOf<HashMap<String, Any>>().apply {
        add(
            hashMapOf(
                "id" to 0,
                "name" to "조용한",
                "image" to R.drawable.icon_filter_quiet,
                "isClicked" to false
            )
        )
        add(
            hashMapOf(
                "id" to 1,
                "name" to "색다른",
                "image" to R.drawable.icon_filter_color,
                "isClicked" to false
            )
        )
        add(
            hashMapOf(
                "id" to 2,
                "name" to "전통적인",
                "image" to R.drawable.icon_filter_traditional,
                "isClicked" to false
            )
        )
        add(
            hashMapOf(
                "id" to 3,
                "name" to "화려한",
                "image" to R.drawable.icon_filter_gorgeous,
                "isClicked" to false
            )
        )
        add(
            hashMapOf(
                "id" to 4,
                "name" to "로맨틱한",
                "image" to R.drawable.icon_filter_romantic,
                "isClicked" to false
            )
        )
        add(
            hashMapOf(
                "id" to 5,
                "name" to "활기찬",
                "image" to R.drawable.icon_filter_energetic,
                "isClicked" to false
            )
        )
        add(
            hashMapOf(
                "id" to 6,
                "name" to "모던한",
                "image" to R.drawable.icon_filter_modern,
                "isClicked" to false
            )
        )
        add(
            hashMapOf(
                "id" to 7,
                "name" to "트렌디한",
                "image" to R.drawable.icon_filter_trendy,
                "isClicked" to false
            )
        )
    }

    //MainActivity 연결 후 pm 생성
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pm = PreferenceManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessToken = (activity as? MainActivity)?.accessToken ?: ""
        Log.d("accessToken_Mypage", accessToken)

        filterInitGridView()
        binding.btnPreferMypage.setOnClickListener{
            mainScope {
                Log.d("filters_retrofit_prefer", filters.toString())
                viewModel.setFilters(accessToken, filters).let {
                    when (it) {
                        is MypageState.Success -> {
                            message = it.data.message
                            Log.d("Set_Filter", message)
                            _filters = filters
                            pm.putFilters(filters)
                            Log.d("Set_Filter", "pm PutFilter Success")
                        }
                        is MypageState.Error -> {
                            Log.d("Set_Filter Error", "${it.exception}")
                        }
                    }
                }
            }
        }
    }

    private fun filterInitGridView() {
        _filters = pm.getFilters()
        _firtstF = _filters[0]
        Log.d("_fitstF", _firtstF)
        _secondF = _filters[1]
        Log.d("_secondF", _secondF)

        val updatedFilterList: MutableList<HashMap<String, Any>> = mutableListOf()
        //_filters 안에 들어가있는거 list 전달
        for (filter in filterList) {
            val name = filter["name"] as String
            val isClicked = (name.equals(_firtstF) || name.equals(_secondF))
            val updatedFilter = HashMap(filter)
            Log.d("updatedFilter_1", updatedFilter.toString())
            updatedFilter["isClicked"] = isClicked
            updatedFilterList.add(updatedFilter)
        }

        Log.d("updatedFilter_2", updatedFilterList.toString())

        gridView = binding.gridViewPreferMypage
        gridView.apply {
            adapter = GridFilterAdapter(requireContext(), updatedFilterList)
        }
        val clickedFilterList = updatedFilterList.filter { it["isClicked"] == true }
        Log.d("clickedFilterList", clickedFilterList.toString())
        filters = clickedFilterList.map { it["name"].toString() }
        Log.d("filters", filters.toString())
        _filters = filters
    }



}