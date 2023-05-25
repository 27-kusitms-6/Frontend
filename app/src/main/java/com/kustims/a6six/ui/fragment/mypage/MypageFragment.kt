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
import com.squareup.picasso.Picasso


class MypageFragment : BaseFragment2<MypageViewModel, FragmentMypageBinding>() {

    override val viewModel: MypageViewModel = MypageViewModel()
    override fun getViewBinding(): FragmentMypageBinding = FragmentMypageBinding.inflate(layoutInflater)

    lateinit var accessToken: String
    lateinit var pm: PreferenceManager

    //getUserInfo
    lateinit var name:String
    lateinit var Email: String
    lateinit var phoneNum: String
    lateinit var nickname: String
    lateinit var filters: List<String>
    lateinit var imgUrl: String
    lateinit var birthDate: String


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

                        nickname = it.data.data.nickname
                        Log.d("mypage Info_Success", nickname)
                        filters = it.data.data.filters
                        Log.d("mypage Info_Success", filters.toString())
                        imgUrl = it.data.data.imgUrl
                        Log.d("mypage Info_Success", imgUrl)
                        Email = it.data.data.email
                        Log.d("mypage Info_Success", Email)
                        name = it.data.data.name
                        Log.d("mypage Info_Success", name)
                        phoneNum = it.data.data.phoneNum
                        Log.d("mypage Info_Success", phoneNum)
                        birthDate = it.data.data.birthDate

                        viewModel.putFilters(pm, filters)
                        viewModel.saveUserInfo(pm, imgUrl, name, nickname, phoneNum, Email, birthDate)
                        _filters = pm.getFilters()

                        if(! _filters.isEmpty()){
                            Log.d("pm_filters", "Success put Filters :"+_filters.toString())
                        } else {
                            Log.d("pm_filters", "Failure put Filters because is Empty")
                        }


                        binding.apply {
                            tvNicknameMypage.text = name
                            tvFilterMypage.text = filters.joinToString(", ") { "#$it" }
                            Picasso.get()
                                .load(imgUrl)
                                .into(binding.ivProfileMypage)
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
                addFragment(LikelistMypageFragment())
                addFragment(MyreviewMypageFragment())
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
            when(position) {
                0 -> tab.text = "   LIKELIST   "
                1 -> tab.text = "   나의 리뷰   "
            }
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

        binding.btnChangeFilterMypage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, PreferMypageFragment())
                .addToBackStack(null)
                .commit()
        }


    }

}




