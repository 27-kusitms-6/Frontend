package com.kustims.a6six


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kustims.a6six.app.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.kustims.a6six.databinding.ActivityMainBinding
import com.kustims.a6six.ui.MainScreen
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContent {
            MainScreen()
        }

        initObserver()
    }

    //life cycle
    fun initObserver()
    {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
            }
        }
    }

}