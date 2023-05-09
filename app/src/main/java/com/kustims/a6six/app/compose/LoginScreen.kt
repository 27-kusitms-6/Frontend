package com.kustims.a6six.ui.Login


import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.kustims.a6six.R
import com.kustims.a6six.app.AuthResultContract
import com.kustims.a6six.app.compose.LoginContent
import com.kustims.a6six.app.viewmodel.LoginViewModel
import com.kustims.a6six.data.model.util.Exceptions
import com.kustims.a6six.data.model.util.RequestState
import com.kustims.a6six.data.model.util.navigateTo
import com.kustims.a6six.data.request.ApiRequest
import com.kustims.a6six.data.response.ApiResponse
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val activity = (LocalContext.current as Activity)
    val signedInState by viewModel.signedInState
    val messageBarState by viewModel.messageBarState
    val apiResponse by viewModel.apiResponse

    Scaffold(topBar = { LoginTopBar() },
        content = {
            LoginContent(
                signedInState = signedInState,
                messageBarState = messageBarState,
                onButtonClicked = {
                    viewModel.saveSignedInState(signedInState = true)
                }
            )
        })

    ActivityResultContracts.StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            Log.d("LoginScreen", tokenId)
            viewModel.verifyTokenOnBackend(ApiRequest(tokenId = tokenId))
        },
        onDialogDismissed = {
            viewModel.saveSignedInState(signedInState = false)
        },
    ) { activityLauncher ->
        if (signedInState) {
            viewModel.signIn(
                activity = activity,
                launchActivityResult = { intentSenderRequest ->
                    activityLauncher.launch(intentSenderRequest)
                },
                accountNotFound = {
                    viewModel.updateMessageBarState(e = Exceptions.GoogleAccountNotFoundException())
                    viewModel.saveSignedInState(signedInState = false)
                }
            )
        }
    }

    LaunchedEffect(key1 = apiResponse) {
        when (apiResponse) {
            is RequestState.Success -> {
                val response = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if (response) {
                    navController.navigateTo(Screen.Profile.route)
                } else {
                    viewModel.saveSignedInState(signedInState = false)
                    (apiResponse as RequestState.Success<ApiResponse>).data.error?.let {
                        viewModel.updateMessageBarState(e = it)
                    }
                }
            }
            else -> {
            }
        }
    }
}