package com.ztx.skeleton.presenter

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ztx.skeleton.BuildConfig.ACCESS_TOKEN_LINK
import com.ztx.skeleton.BuildConfig.AUTHORIZATION_LINK
import com.ztx.skeleton.BuildConfig.CLIENT_ID
import com.ztx.skeleton.BuildConfig.CLIENT_SECRET
import com.ztx.skeleton.BuildConfig.REDIRECT_URI
import com.ztx.skeleton.R
import com.ztx.skeleton.domain.model.TokenManager
import com.ztx.skeleton.presenter.screen.SignInScreen
import com.ztx.skeleton.presenter.ui.theme.SkeletonTheme
import com.ztx.skeleton.presenter.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.AuthState
import net.openid.appauth.AuthState.AuthStateAction
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationService.TokenResponseCallback
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientAuthentication
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.ResponseTypeValues
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : ComponentActivity() {

    private lateinit var authState: AuthState
    private lateinit var authService: AuthorizationService
    private var authResponse: AuthorizationResponse? = null
    private var authException: AuthorizationException? = null

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Skeleton)
        setContent {
            SkeletonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignInScreen(
                        onSignInClick = { requestAuthorizationCode() }
                    )
                }
            }
        }
    }

    private fun configAuthorizationService(): AuthorizationServiceConfiguration {
        return AuthorizationServiceConfiguration(
            Uri.parse(AUTHORIZATION_LINK),
            Uri.parse(ACCESS_TOKEN_LINK)
        )
    }

    private fun configAuthRequest(): AuthorizationRequest {
        val serviceConfig = configAuthorizationService()
        authState = AuthState(serviceConfig)

        val redirectUri = Uri.parse(REDIRECT_URI)
        return AuthorizationRequest.Builder(
            serviceConfig,
            CLIENT_ID,
            ResponseTypeValues.CODE,
            redirectUri
        ).setScope("offline_access").build()
    }

    private val authActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleActivityResult(result)
        }

    private fun handleActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                authResponse = AuthorizationResponse.fromIntent(data)
                authResponse?.let { authResponse ->
                    requestToken(authService, authResponse)
                }
                authException = AuthorizationException.fromIntent(data)
                authException?.let {
                    showToast("Error when authorization: ${it.message}")
                }
                authState.update(authResponse, authException)
            }
        } else {
            showToast("Error when requesting the authorization code")
        }
    }

    private fun requestAuthorizationCode() {
        val authRequest = configAuthRequest()
        authService = AuthorizationService(this)
        val authIntent = authService.getAuthorizationRequestIntent(authRequest)

        authActivityResultLauncher.launch(authIntent)
    }

    private fun requestToken(authService: AuthorizationService, response: AuthorizationResponse) {
        val clientAuthentication: ClientAuthentication =
            ClientSecretBasic(CLIENT_SECRET)
        val tokenRequest = response.createTokenExchangeRequest()
        val callback = TokenResponseCallback { response, exception ->
            if (response != null) {
                // retrieveToken()
                response.accessToken?.let { token ->
                    tokenManager.setToken(token)
                    finish()
                    MainActivity.start(this)
                }
            } else {
                showToast("Error when requesting the token")
            }
            authState.update(response, exception)
        }

        authService.performTokenRequest(tokenRequest, clientAuthentication, callback)
    }

    private fun retrieveToken() {
        authState.performActionWithFreshTokens(authService,
            AuthStateAction { accessToken, idToken, exception ->
                if (exception != null) {
                    showToast("Error when negotiating for fresh tokens: ${exception.message}")
                    return@AuthStateAction
                }
                accessToken?.let {
                    tokenManager.setToken(it)
                    finish()
                    MainActivity.start(this)
                }
            }
        )
    }
}
