package com.harman.interview.services.auth.email

import android.content.Context
import com.harman.interview.R
import com.harman.interview.api.LoginApi
import com.harman.interview.mvvm.model.LoginRequest
import com.harman.interview.mvvm.model.LoginResponse
import com.harman.interview.services.WebService
import com.harman.interview.utils.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Email Auth Engine
 * @author vikramezhil
 */

open class EmailAuthEngine(private val context: Context) {

    /**
     * Triggers the email authentication
     * @param loginRequest The login request
     * @callback listener The class instance which implements the email auth listener
     */
    protected fun triggerEmailAuthenticate(loginRequest: LoginRequest, listener: EmailAuthListener) {
        listener.onEmailAuthInProgress(true)
        WebService.client.create(LoginApi::class.java).login(loginRequest).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                listener.onEmailAuthInProgress(false)
                if (response.isSuccessful && response.body() != null) {
                    listener.onEmailAuthResponse(response)
                } else if (response.code() == 401) {
                    listener.onEmailAuthError(context.resources.getString(R.string.loginUnAuthorizedError))
                } else {
                    listener.onEmailAuthError(context.resources.getString(R.string.loginAuthError))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                listener.onEmailAuthInProgress(false)
                if(Network.isAvailable(context)) {
                    listener.onEmailAuthError(context.resources.getString(R.string.loginAuthError))
                } else {
                    listener.onEmailAuthError(context.resources.getString(R.string.networkError))
                }
            }
        })
    }
}