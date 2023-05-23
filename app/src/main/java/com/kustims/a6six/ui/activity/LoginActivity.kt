package com.kustims.a6six.ui.activity


import android.app.Activity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.credentials.IdToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kustims.a6six.data.Constants
import com.kustims.a6six.R
import com.kustims.a6six.data.model.response.LoginGoogleResponse
import com.kustims.a6six.data.model.response.LoginResponse
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.ActivityLoginBinding
import com.kustims.a6six.databinding.ActivityMainBinding
import com.kustims.a6six.ui.viewmodel.LoginViewModel
import com.kustims.a6six.ui.viewmodelstate.LoginState
import dagger.hilt.android.AndroidEntryPoint


class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var idToken: String

    //Login Response
    private lateinit var accessToken: String
    private lateinit var refreshToken: String
    private var signUp: Boolean = false
    private var id: Int = 0

    private val viewModel:LoginViewModel by viewModels()

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private val pm: PreferenceManager = PreferenceManager(this)

    override fun onStart() {
        super.onStart()
        mainScope {  }
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            mGoogleSignInClient?.signOut()?.addOnCompleteListener(this) {
                //Logout Success
                Log.d("GOOGLE_LOGIN", "로그인된 계정 로그아웃 완료")
            }
        }
    }

    fun mainScope(block: suspend () -> Unit) {
        lifecycleScope.launchWhenCreated {
            block.invoke()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val googleClientId = Constants.GOOGLE_CLIENT_ID
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleClientId)
            .requestEmail()
            .build()

        loginLauncher = registerForActivityResult(
            StartActivityForResult()
        )
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        binding.googleLogin.setOnClickListener { view ->
            when (view.id) {
                R.id.google_login -> loginwithGoogle()
            }
        }

    }

    private fun loginwithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        loginLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completeTask: Task<GoogleSignInAccount>) {

        try {
            Log.d("authCode line 1", "pass1")
            val authCode = completeTask.getResult(ApiException::class.java)?.serverAuthCode
            mainScope {
                authCode?.run {
                    Log.d("authCode line 2", "pass2")
                    viewModel.fetchGoogleAuthInfo(this).let {
                        when(it) {
                            is LoginState.Success<LoginGoogleResponse> -> {
                                Log.d("Success", "${it.data}")
                                accessToken = it.data.access_token
                                Log.d("accessToken", accessToken)
                            }
                            is LoginState.Error ->
                                Log.d("Error", "${it.exception}")
                        }
                    }
                    Log.d("authCode line 3", "pass3")
                    viewModel.fetchAuthInfo(accessToken).let {
                        when(it) {
                            is LoginState.Success<LoginResponse> -> {

                                Log.d("Success", "${it.data}")
                                accessToken = it.data.atk
                                refreshToken = it.data.rtk
                                signUp = it.data.signUp
                                id = it.data.id
                                Log.d("id", id.toString())
                                Log.d("signUp", signUp.toString())
                                Log.d("refreshToken", refreshToken)
                                Log.d("accessToken", accessToken)
                                //accessToken & refreshToken 저장
                                viewModel.saveAuthToken(pm, accessToken, refreshToken)
                                Log.d("authCode line 4", "pass4")
                                //Main으로 이동
                                moveToMain()
                            }
                            is LoginState.Error ->
                                Log.d("Error", "${it.exception}")
                        }
                    }
                        ?:Log.e("구글 서버 인증 실패", "Authentication Failed")
                }
            }

            val account = completeTask.getResult(ApiException::class.java)
            idToken = account?.idToken.toString()
            val email = account?.email.toString()
            val displayName = account?.displayName.toString()

            Log.d("Google_idToken",idToken)
            Log.d("Google_Account", email)
            Log.d("Google_displayName", displayName)


            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } catch (e: ApiException) {
            Log.e("로그인 실패", "SignInResult : failed Code" + e.statusCode)
        }


    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
