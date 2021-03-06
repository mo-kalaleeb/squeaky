package com.sixbits.assessment.feature.auth.presenter

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.sixbits.assessment.R
import com.sixbits.assessment.core.navigation.Navigator
import com.sixbits.assessment.databinding.FragmentLoginBinding
import com.sixbits.authenticator.AuthGuard
import com.sixbits.platform.core.BaseFragment
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var authGuard: AuthGuard

    private lateinit var uiBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        uiBinding = FragmentLoginBinding.inflate(
            layoutInflater,
            container,
            false
        )

        setupUI()
        setupListeners()

        return uiBinding.root
    }

    private fun setupUI() {
        uiBinding.buttonLogin.setOnClickListener {
            startSpotifyLoginActivity()
        }
    }

    private fun setupListeners() {
        loginViewModel.loggedInLiveData.observe(viewLifecycleOwner, {
            if (it) {
                navigator.showMain(requireContext())
            }
        })
    }

    private fun startSpotifyLoginActivity() {
        val spotifyClientId = getString(R.string.spotify_key)

        val authRequest = AuthorizationRequest.Builder(
            spotifyClientId,
            AuthorizationResponse.Type.TOKEN, Uri.Builder()
                .scheme(getString(R.string.com_spotify_sdk_redirect_scheme))
                .authority(getString(R.string.com_spotify_sdk_redirect_host))
                .build().toString()
        ).setShowDialog(true)
            .setScopes(arrayOf("user-read-email"))
            .setCampaign("your-campaign-token")
            .build()

        val intent = AuthorizationClient.createLoginActivityIntent(requireActivity(), authRequest)
        getAuthToken.launch(intent)
    }

    private val getAuthToken = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val response = AuthorizationClient.getResponse(it.resultCode, it.data)

        loginViewModel.loginUser(response)
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
