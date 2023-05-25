package com.kustims.a6six.ui.fragment.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import com.kustims.a6six.base.BaseFragment
import com.kustims.a6six.base.BaseFragment2
import com.kustims.a6six.data.model.response.GetBookMarkResponse
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.FragmentPreferMypageBinding
import com.kustims.a6six.databinding.FragmentSaveDetailBinding
import com.kustims.a6six.domain.model.SaveDetail
import com.kustims.a6six.ui.activity.MainActivity
import com.kustims.a6six.ui.adapter.GridBookAdapter
import com.kustims.a6six.ui.adapter.GridFilterAdapter
import com.kustims.a6six.ui.viewmodel.MypageViewModel
import com.kustims.a6six.ui.viewmodelstate.MypageState


class SaveDetailFragment : BaseFragment2<MypageViewModel, FragmentSaveDetailBinding>() {


    private lateinit var gridView: GridView
    lateinit var accessToken: String
    lateinit var pm: PreferenceManager

    var resId: Int = 0
    val saveList = mutableListOf<HashMap<String,Any>>()
    lateinit var img: String
    lateinit var name: String


    override val viewModel: MypageViewModel = MypageViewModel()
    override fun getViewBinding(): FragmentSaveDetailBinding =
        FragmentSaveDetailBinding.inflate(layoutInflater)

    //MainActivity 연결 후 pm 생성
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pm = PreferenceManager(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accessToken = (activity as? MainActivity)?.accessToken ?: ""
        Log.d("accessToken_Mypage", accessToken)

        initViews()

        mainScope {
            viewModel.getBookmark(accessToken).let { response ->
                when(response) {
                    is MypageState.Success -> {
                        val data = response.data.data
                        data.forEach { item  ->
                            val hashMap = HashMap<String, Any>()
                            hashMap["id"] = item.id
                            hashMap["name"] = item.name
                            hashMap["image"] = item.img
                            saveList.add(hashMap)
                            filterInitGridView()
                        }

                    }
                    is MypageState.Error -> {
                        Log.d("getInfo Error", "${response.exception}")
                    }
                    else -> {}
                }
            }
        }

    }

    private fun filterInitGridView() {
        gridView = binding.recyclerviewSaveDetail
        gridView.apply {
            adapter = GridBookAdapter(requireContext(), saveList)
        }
    }


}

