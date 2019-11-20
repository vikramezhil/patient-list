package com.harman.interview.services.auth.email

import com.harman.interview.mvvm.model.LoginResponse
import retrofit2.Response


interface EmailAuthListener {
    /**
     * Sends an update on the email auth progress
     * @param progress The email auth running progress
     */
    fun onEmailAuthInProgress(progress: Boolean)

    /**
     * Sends an update on the email auth response
     * @param response The email auth login response
     */
    fun onEmailAuthResponse(response: Response<LoginResponse>)

    /**
     * Sends an update on the email auth error
     * @param errMsg The email auth error message
     */
    fun onEmailAuthError(errMsg: String)
}