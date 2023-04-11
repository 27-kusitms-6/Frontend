package com.kustims.a6six.Login

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.kustims.a6six.R



class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(Integer.parseInt(kakao_native_key)))
    }

    companion object {
        val kakao_native_key: String ="d5b4a089c1d45c04158ccb932987e10f"
    }
}