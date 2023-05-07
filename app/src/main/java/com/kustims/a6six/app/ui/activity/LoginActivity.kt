package com.kustims.a6six.app.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.kustims.a6six.MainActivity
import com.kustims.a6six.ui.Login.LoginScreen

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(onLoginSuccess = { onLoginSuccess() }) // import androidx.activity.compose.setContent Only
        }
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}