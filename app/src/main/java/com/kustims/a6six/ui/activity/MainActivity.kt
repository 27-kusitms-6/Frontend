package com.kustims.a6six.ui.activity


import android.os.Bundle
import com.kustims.a6six.databinding.ActivityMainBinding
import com.kustims.a6six.ui.fragment.Editor.EditorFragment
import com.kustims.a6six.ui.fragment.home.HomeFragment
import com.kustims.a6six.ui.fragment.mypage.MypageFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kustims.a6six.R
import androidx.appcompat.app.AppCompatActivity



class MainActivity: AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fcv_main, fragment)
        transaction.commit()
    }
}
