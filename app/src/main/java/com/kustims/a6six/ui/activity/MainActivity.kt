package com.kustims.a6six.ui.activity

import BaseActivity
import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD:app/src/main/java/com/kustims/a6six/app/ui/activity/MainActivity.kt
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
=======
import androidx.appcompat.app.AppCompatActivity
>>>>>>> 6d67c2b2df8ca98c5e969b80f866db10b25e0650:app/src/main/java/com/kustims/a6six/ui/activity/MainActivity.kt
import com.kustims.a6six.databinding.ActivityMainBinding
import com.kustims.a6six.ui.viewmodel.MainViewModel

<<<<<<< HEAD:app/src/main/java/com/kustims/a6six/app/ui/activity/MainActivity.kt

class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>() {

    lateinit var bottomNav : BottomNavigationView

    override val viewModel: MainViewModel
        get() = MainViewModel()


    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
=======

class MainActivity: AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
>>>>>>> 6d67c2b2df8ca98c5e969b80f866db10b25e0650:app/src/main/java/com/kustims/a6six/ui/activity/MainActivity.kt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
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
<<<<<<< HEAD:app/src/main/java/com/kustims/a6six/app/ui/activity/MainActivity.kt



    }

=======
}
>>>>>>> 6d67c2b2df8ca98c5e969b80f866db10b25e0650:app/src/main/java/com/kustims/a6six/ui/activity/MainActivity.kt
