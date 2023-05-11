package com.kustims.a6six.app.ui.activity


import android.app.Activity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.kustims.a6six.BuildConfig
import com.kustims.a6six.R
import com.kustims.a6six.app.viewmodel.LoginViewModel
import com.kustims.a6six.app.viewmodelstate.LoginState
import com.kustims.a6six.databinding.ActivityLoginBinding
import com.kustims.a6six.domain.model.LoginGoogleResponse
import com.kustims.a6six.domain.model.LoginSlothResponse


class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel
        get() = LoginViewModel()

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>

    private lateinit var accessToken: String

    private lateinit var refreshToken: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val googleClientId = BuildConfig.GOOGLE_CLIENT_ID
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(Scopes.DRIVE_APPFOLDER))
            .requestIdToken(googleClientId)
            .requestServerAuthCode(googleClientId)
            .requestEmail()
            .build()

        loginLauncher = registerForActivityResult(
            StartActivityForResult()
        )
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                handleSignInResult(task)
            }
        }

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

//        binding.googleLogin.setOnClickListener { view ->
//            when (view.id) {
//                R.id.google_login -> loginWithGoogle()
//            }
//        }


        }

    private fun loginwithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        loginLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val authCode = completedTask.getResult(ApiException::class.java)?.serverAuthCode

            mainScope {
                authCode?.run {
                    viewModel.fetchGoogleAuthInfo(this).let {
                        when (it) {
                            is LoginState.Success<LoginGoogleResponse> -> {
                                Log.d("Success", "${it.data}")
                                accessToken = it.data.access_token
                                Log.d("accessToken", accessToken)
                            }

                            is LoginState.Error ->
                                Log.d("Error", "${it.exception}")
                        }
                    }
                    viewModel.fetchSlothAuthInfo(accessToken, "GOOGLE").let {
                        when (it) {
                            is LoginState.Success<LoginSlothResponse> -> {
                                Log.d("Success", "${it.data}")
                                accessToken = it.data.accessToken
                                refreshToken = it.data.refreshToken

                                Log.d("accessToken", accessToken)
                                Log.d("refreshToken", refreshToken)

                            }
                            is LoginState.Error ->
                                Log.d("Error", "${it.exception}")
                        }
                    }
                } ?: Log.e("구글 서버 인증 실패", "Authentication failed")
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("로그인 실패", "signInResult:failed code=" + e.statusCode)
        }
    }
}

