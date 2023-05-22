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


        val firstFragment=EditorFragment()
        val secondFragment=HomeFragment()
        val thirdFragment=MypageFragment()

        setCurrentFragment(secondFragment)

        binding.menuMain.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fragment_editor->setCurrentFragment(firstFragment)
                R.id.fragment_home->setCurrentFragment(secondFragment)
                R.id.fragment_mypage->setCurrentFragment(thirdFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_main,fragment)
            commit()
        }

}
