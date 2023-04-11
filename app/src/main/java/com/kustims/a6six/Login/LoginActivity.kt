package com.kustims.a6six.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient
import com.kustims.a6six.R
import java.security.AuthProvider

class LoginActivity : AppCompatActivity() {

    lateinit var kakaoCallback: (OAuthToken?, Throwable?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun btnKakaoLogin(view: View) {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인

    }

    fun KakaoCallback() {
        kakaoCallback = { token, error ->
            if (error != null) {
                when {
                    //Access Denied
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Log.d("[카카오로그인]", "접근 거부됨(동의 취소)");
                    }
                    //Invalid - Client
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Log.d("[카카오로그인]", "유효하지 않은 앱");
                    }
                    //Invalid - Grant (Not certified"
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Log.d("[카카오로그인]", "인증 수단이 유효하지 않아 인증할 수 없는 상태");
                    }
                    //Invalid - Parameter Error
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Log.d("[카카오로그인]", "요청 파라미터 오류");
                    }
                    //Invalid - Scope
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Log.d("[카카오로그인]", "유효하지 않은 scope ID");
                    }
                    //Invalid - Setting Error(Hash Key)
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Log.d("[카카오로그인]", "설정이 올바르지 않음(android key hash)");
                    }
                    //Server Error
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Log.d("[카카오로그인]", "서버 내부 에러");
                    }
                    //App doesn't Have Access
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Log.d("[카카오로그인]", "앱이 요청 권한이 없음");
                    }
                    else -> {
                        //Unknown Error
                        Log.d("[카카오로그인]", "Unknown Error")
                    }
                }
            }
            else if (token != null) {
                Log.d("[카카오로그인]", "로그인에 성공하였습니다.\n${token.accessToken}")
                UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                    UserApiClient.instance.me { user, error ->
                        Log.d("[카카오로그인]", "닉네임: ${user?.kakaoAccount?.profile?.nickname}" +
                                "\n이메일: ${user?.kakaoAccount?.email}"
                        + "\n회원번호: ${user?.id}")
                    }
                }
            }
            else { Log.d("카카오로그인", "token == null && Error == null ") }
        }
    }

    //Kakao LogOut
    fun kakaoLogOut() {
        UserApiClient.instance.logout { error ->
            if(error != null) {
                Log.d("카카오 로그아웃", "카카오 로그아웃 실패")
            } else {
                Log.d("카카오 로그아웃", "카카오 로그아웃 성공")
            }
        }
    }

    //KakaoExit
    fun kakaoUnlink() {
        UserApiClient.instance.unlink { error->
            if(error != null) {
                Log.d("카카오 회원관리", "회원 탈퇴 실패")
            } else {
                Log.d("카카오 회원관리", "회원 탈퇴 성공")
            }
        }
    }
}