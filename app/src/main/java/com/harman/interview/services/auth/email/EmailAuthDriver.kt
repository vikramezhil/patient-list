package com.harman.interview.services.auth.email

import android.content.Context
import com.harman.interview.mvvm.model.LoginRequest

/**
 * Email Auth Driver
 * @author vikramezhil
 */

class EmailAuthDriver(context: Context): EmailAuthEngine(context) {

    /**
     * Triggers the email authentication
     * @param email The email
     * @param password The password
     * @param listener The class instance which implements the email auth listener
     */
    fun emailAuthenticate(email: String, password: String, listener: EmailAuthListener) {
        val loginRequest = LoginRequest(email, password)
        triggerEmailAuthenticate(loginRequest, listener)
    }
}