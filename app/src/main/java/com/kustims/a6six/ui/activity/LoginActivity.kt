package com.kustims.a6six.ui.activity




import com.kustims.a6six.base.BaseActivity
import android.app.Activity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kustims.a6six.R
import com.kustims.a6six.data.Constants
import com.kustims.a6six.data.model.response.LoginGoogleResponse
import com.kustims.a6six.data.model.response.LoginResponse
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.databinding.ActivityLoginBinding
import com.kustims.a6six.ui.viewmodel.LoginViewModel
import com.kustims.a6six.ui.viewmodelstate.LoginState


class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getViewBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
    override val viewModel: LoginViewModel
        get() = LoginViewModel()


    private lateinit var idToken: String
    private lateinit var message: String

    //Login Response
    private lateinit var accessToken: String
    private lateinit var refreshToken: String
    private var signUp: Boolean = false
    private var id: Int = 0


    private var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private val pm: PreferenceManager = PreferenceManager(this)

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            mGoogleSignInClient?.signOut()?.addOnCompleteListener(this) {
                //Logout Success
                Log.d("GOOGLE_LOGIN", "로그인된 계정 로그아웃 완료")
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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

        val account = completeTask.getResult(ApiException::class.java)
        idToken = account?.idToken.toString()
        val email = account?.email.toString()
        val displayName = account?.displayName.toString()


        try {
            val authCode = completeTask.getResult(ApiException::class.java)?.serverAuthCode
            Log.d("authCode", authCode.toString())
            mainScope {

                viewModel.fetchAuthInfo(idToken).let {
                    when(it) {
                        is LoginState.Success<LoginResponse> -> {
                            Log.d("PASS4", "LoginState Success & Access token 성공")
                            Log.d("Success", "${it.data}")
                            accessToken = it.data.data.atk
                            refreshToken = it.data.data.rtk
                            signUp = it.data.data.signUp
                            id = it.data.data.id
                            message = it.data.message

                            Log.d("accessToken", accessToken)
                            Log.d("refreshToken", refreshToken)
                            Log.d("signUp", signUp.toString())
                            Log.d("id", id.toString())
                            Log.d("message", message)

                            //accessToken & refreshToken 저장
                            Log.d("PASS5", "datastore 성공")
                            viewModel.saveAuthToken(pm, accessToken, refreshToken)

                            //Main으로 이동
                            moveToMain()
                        }
                        is LoginState.Error ->
                            Log.d("Error", "${it.exception}")
                    }
                }

                Log.d("Google_idToken", idToken)
                Log.d("Google_Account", email)
                Log.d("Google_displayName", displayName)
            }


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
