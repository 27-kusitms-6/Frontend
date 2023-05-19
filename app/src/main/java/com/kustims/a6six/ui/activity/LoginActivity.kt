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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kustims.a6six.data.Constants
import com.kustims.a6six.R
import com.kustims.a6six.databinding.ActivityLoginBinding
import com.kustims.a6six.databinding.ActivityMainBinding
import com.kustims.a6six.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel:LoginViewModel by viewModels()

    private var mGoogleSignInClient: GoogleSignInClient? = null

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>

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

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        loginLauncher = registerForActivityResult(
            StartActivityForResult()
        )
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }
    }

    private fun loginwithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
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




}
