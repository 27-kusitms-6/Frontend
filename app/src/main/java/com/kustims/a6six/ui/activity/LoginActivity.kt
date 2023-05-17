package com.kustims.a6six.ui.activity


import android.app.Activity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kustims.a6six.data.Constants
import com.kustims.a6six.R
import com.kustims.a6six.app.Base.BaseActivity
import com.kustims.a6six.ui.viewmodel.LoginViewModel
import com.kustims.a6six.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModel: LoginViewModel
        get() = LoginViewModel()

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>

//    private lateinit var accessToken: String
//
//    private lateinit var refreshToken: String


//    override fun onStart() {
//        super.onStart()
//        val gsa = GoogleSignIn.getLastSignedInAccount(this)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loginLauncher = registerForActivityResult(
            StartActivityForResult()
        )
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }
        val googleClientId = Constants.GOOGLE_CLIENT_ID

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleClientId)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)





        binding.googleLogin.setOnClickListener { view ->
            when (view.id) {
                R.id.google_login -> loginwithGoogle()
            }
        }


        }

    private fun loginwithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        loginLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completeTask: Task<GoogleSignInAccount>) {
        try {
            val account = completeTask.getResult(ApiException::class.java)
            val idToken = account?.idToken.toString()
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



    override fun onStart() {
        super.onStart()
        val gsa = GoogleSignIn.getLastSignedInAccount(this)

        if (gsa != null) {

        }
    }

}

