package com.kustims.a6six

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.kustims.a6six.Login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkKakaoUserCheck();

        //Kakao login - key hash check code
        Log.d(TAG, "keyhash : ${Utility.getKeyHash(this)}")
    }

    fun checkKakaoUserCheck() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            //tokenInfo == null (Login X)
            if(error != null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            //Login O
            else if (tokenInfo != null) {

            }
        }
    }
}