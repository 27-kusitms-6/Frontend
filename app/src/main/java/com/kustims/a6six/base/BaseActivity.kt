package com.kustims.a6six.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.kustims.a6six.app.Base.BaseViewModel
import kotlinx.coroutines.Job
import timber.log.Timber

abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding> : AppCompatActivity() {
    abstract val viewModel: VM

    protected lateinit var binding: VB

    private lateinit var fetchJob: Job

    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initState()
    }

    //상태 값을 초기화
    open fun initState() {
        initViews()
        fetchJob = viewModel.fetchData()
        observeData()
    }


    open fun initViews() = Unit

    open fun observeData() = Unit

    fun mainScope(block: suspend () -> Unit) {
        lifecycleScope.launchWhenCreated {
            block.invoke()
        }
    }

    override fun onDestroy() {
        if(fetchJob.isActive){
            fetchJob.cancel()
        }
        super.onDestroy()
    }


}