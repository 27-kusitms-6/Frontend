package com.kustims.a6six.app.viewmodel


import android.app.Activity
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kustims.a6six.data.model.MessageBarState
import com.kustims.a6six.data.model.User
import com.kustims.a6six.data.model.UserUpdate
import com.kustims.a6six.data.model.util.Exceptions
import com.kustims.a6six.data.model.util.RequestState
import com.kustims.a6six.data.model.util.oneTapSignIn
import com.kustims.a6six.data.model.util.updateWith
import com.kustims.a6six.data.request.ApiRequest
import com.kustims.a6six.data.response.ApiResponse
import com.kustims.a6six.domain.repository.AuthRepository
import com.kustims.a6six.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    private val _apiResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)
    val apiResponse: State<RequestState<ApiResponse>> = _apiResponse

    private val _clearSessionResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)
    val clearSessionResponse: State<RequestState<ApiResponse>> = _clearSessionResponse

    private val _user: MutableState<User?> = mutableStateOf(null)
    val user: State<User?> = _user

    private val _firstName: MutableState<String> = mutableStateOf("")
    val firstName: State<String> = _firstName

    private val _lastName: MutableState<String> = mutableStateOf("")
    val lastName: State<String> = _lastName


    private val _messageBarState: MutableState<MessageBarState> =
        mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            _apiResponse.value = RequestState.Loading
            try {
                val response = userRepository.getUserInfo()
                //Response 처리
                if (response.user != null) {
                    _user.value = response.user
                    _firstName.value = response.user.name.split(" ").first()
                    _lastName.value = response.user.name.split(" ").last()
                }
                updateApiResponseSuccessAndMessageBar(response)
            } catch (e: Exception) {
                catchApiResponseException(e)
            }
        }
    }
    fun updateUserInfo(firstName: String, lastName: String) {
        viewModelScope.launch {
            _apiResponse.value = RequestState.Loading
            val currentUser = user.value
            try {
                if (currentUser!!.name.split(" ").first() == firstName.trim()
                    && currentUser.name.split(" ").last() == lastName.trim()
                ) {
                    throw Exceptions.InvalidUserException("Nothing to update")
                }
                val userUpdate = UserUpdate(firstName.trim(), lastName.trim())
                val response = userRepository.updateUser(userUpdate)
                _user.value = User(
                    id = user.value!!.id,
                    name = "${firstName.trim()} ${lastName.trim()}",
                    email = user.value!!.email,
                    picture = user.value!!.picture,
                    given_name = user.value!!.given_name,
                    locale =  user.value!!.locale,
                    verified_email = user.value!!.verified_email
                )
                updateApiResponseSuccessAndMessageBar(response)
            } catch (e: Exception) {
                catchApiResponseException(e)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _clearSessionResponse.value = RequestState.Loading
            try {
                val response: ApiResponse = userRepository.clearSession()
                _clearSessionResponse.value = RequestState.Success(data = response)
                updateApiResponseSuccessAndMessageBar(response)
            } catch (e: Exception) {
                _clearSessionResponse.value = RequestState.Error(t = e)
                catchApiResponseException(e)
            }
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            _clearSessionResponse.value = RequestState.Loading
            try {
                val response: ApiResponse = userRepository.deleteUser()
                _clearSessionResponse.value = RequestState.Success(data = response)
                updateApiResponseSuccessAndMessageBar(response)
            } catch (e: Exception) {
                _clearSessionResponse.value = RequestState.Error(t = e)
                catchApiResponseException(e)
            }
        }

    }

    fun verifyTokenOnBackend(request: ApiRequest) {
        _apiResponse.value = RequestState.Loading
        try {
            viewModelScope.launch {
                val response = userRepository.verifyUserOnBackend(request)
                _apiResponse.value = RequestState.Success(data = response)
                _messageBarState.value = MessageBarState(
                    message = response.message,
                )
            }
        } catch (e: Exception) {
            _apiResponse.value = RequestState.Error(t = e)
            _messageBarState.value = MessageBarState(
                error = e,
            )
        }
    }

    fun signIn(
        activity: Activity,
        launchActivityResult: (IntentSenderRequest) -> Unit,
        accountNotFound: (Exception) -> Unit
    ) {
        oneTapSignIn(
            activity = activity,
            launchActivityResult = launchActivityResult,
            setFilterByAuthorizedAccounts = true,
            setAutoSelectEnabled = true,
            addOnFailure = {
                // It means that not able to find the proper Google account on the smartphone.
                // So signUp is needed:
                Log.d("SignIn", "Signing up...")
                signUp(
                    activity = activity,
                    launchActivityResult = launchActivityResult,
                    accountNotFound = accountNotFound
                )
            }
        )
    }

    private fun signUp(
        activity: Activity,
        launchActivityResult: (IntentSenderRequest) -> Unit,
        accountNotFound: (Exception) -> Unit
    ) {
        oneTapSignIn(
            activity = activity,
            launchActivityResult = launchActivityResult,
            setFilterByAuthorizedAccounts = false,
            setAutoSelectEnabled = false,
            addOnFailure = { exception ->
                // It means that not there is no Google account on the smartphone (very low possibility).
                Log.d("SignUp", exception.message.toString())
                accountNotFound(exception)
            }
        )
    }

    private fun updateApiResponseSuccessAndMessageBar(response: ApiResponse) {
        _apiResponse.value = RequestState.Success(data = response)
        _messageBarState.value = MessageBarState(
            message = response.message,
            error = response.error
        )
    }

    private fun catchApiResponseException(e: Exception) {
        _apiResponse.value = RequestState.Error(t = e)
        _messageBarState.value = MessageBarState(
            error = e
        )
    }

    fun saveSignedInState(signedInState: Boolean) {
        viewModelScope.launch {
            authRepository.saveSignedInState(signedInState)
        }
    }

    fun updateFirstName(entry: String) {
        _firstName.updateWith(entry)
    }

    fun updateLastName(entry: String) {
        _lastName.updateWith(entry)
    }
}