package com.kustims.a6six.app.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kustims.a6six.R
import com.kustims.a6six.app.ui.fragment.Editor.EditorFragment
import com.kustims.a6six.app.ui.fragment.home.HomeFragment
import com.kustims.a6six.app.ui.fragment.mypage.MypageFragment
import com.kustims.a6six.app.viewmodel.BottomNavigationViewModel
import com.kustims.a6six.app.viewmodel.MainViewModel
import com.kustims.a6six.databinding.ActivityMainBinding


class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {

    lateinit var bottomNav : BottomNavigationView

    override val viewModel: MainViewModel
        get() = MainViewModel()


    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        bottomNav = findViewById(R.id.menu_main) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.fragment_editor -> {
                    loadFragment(EditorFragment())
                    true
                }

                R.id.fragment_mypage -> {
                    loadFragment(MypageFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }


    }

    private fun loadFragment(fragment: Fragment) {
        val transaction  =supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fcv_main, fragment)
        transaction.commit()
    }



    }

