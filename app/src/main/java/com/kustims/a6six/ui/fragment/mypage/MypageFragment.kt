package com.kustims.a6six.ui.fragment.mypage

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kustims.a6six.R
import com.kustims.a6six.base.BaseFragment2
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.FragmentMypageBinding
import com.kustims.a6six.ui.activity.MainActivity
import com.kustims.a6six.ui.adapter.MypagePagerFragmentStateAdapter
import com.kustims.a6six.ui.viewmodel.MypageViewModel
import com.kustims.a6six.ui.viewmodelstate.MypageState


class MypageFragment : BaseFragment2<MypageViewModel, FragmentMypageBinding>() {

    override val viewModel: MypageViewModel = MypageViewModel()
    override fun getViewBinding(): FragmentMypageBinding = FragmentMypageBinding.inflate(layoutInflater)

    lateinit var accessToken: String
    lateinit var pm: PreferenceManager

    //getUserInfo
    lateinit var nickname: String
    lateinit var filters: List<String>
    lateinit var imgUrl: String


    //pm 검증용
    lateinit var _filters : List<String>

    //MainActivity 연결 후 pm 생성
    override fun onAttach(context: Context) {
        super.onAttach(context)
        pm = PreferenceManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessToken = (activity as? MainActivity)?.accessToken ?: ""
        Log.d("accessToken_Mypage", accessToken)

        filters = (activity as? MainActivity)?.filters ?: emptyList()
        Log.d("filters_Mypage", filters.toString())

        initViews()


        //coroutine Viewmodel
        mainScope {
            Log.d("mypage_Scope", "Success")
            viewModel.getUserInfo(accessToken).let {
                when(it) {
                    is MypageState.Success -> {
                        Log.d("getInfo Success", "{it.data}")

                        nickname = it.data.data.name
                        Log.d("mypafe Info_Success", nickname)
                        filters = it.data.data.filters
                        Log.d("mypafe Info_Success", filters.toString())
                        imgUrl = it.data.data.imgUrl
                        Log.d("mypafe Info_Success", imgUrl)

                        viewModel.putFilters(pm, filters)
                        _filters = pm.getFilters()
                        if(! _filters.isEmpty()){
                            Log.d("pm_filters", "Success put Filters :"+_filters.toString())
                        } else {
                            Log.d("pm_filters", "Failure put Filters")
                        }


                        binding.apply {
                            tvNicknameMypage.text = nickname
                            tvFilterMypage.text = filters.toString()
                            ivProfileMypage.setImageURI(Uri.parse(imgUrl))
                        }
                    }

                    is MypageState.Error -> {
                        Log.d("getInfo Error", "${it.exception}")
                    }
                }
            }

        }

        val pagerAdapter = MypagePagerFragmentStateAdapter(this)
            .apply {
                addFragment(MyreviewMypageFragment())
                addFragment(LikelistMypageFragment())
            }

        val viewPager: ViewPager2 = binding.viewPagerMypage.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d("ViewPagerFragment" , "Page ${position+1}")
                }
            })
        }

        TabLayoutMediator(binding.tabLayoutMypage, viewPager) { tab, position ->
            tab.text = "${position}"
        }.attach()



        binding.settingTopbarMypage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, SettingsMypageFragment())
                .addToBackStack(null)
                .commit()

        }

        binding.btnProfileEditMypage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, AccountEditMypageFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.tvFilterMypage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, PreferMypageFragment())
                .addToBackStack(null)
                .commit()
        }

    }

}




