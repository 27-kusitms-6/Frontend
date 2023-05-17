package com.kustims.a6six.app.Base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class LikeIt: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@LikeIt)
            //Timber 초기화
            Timber.plant(Timber.DebugTree())
        }
    }



}