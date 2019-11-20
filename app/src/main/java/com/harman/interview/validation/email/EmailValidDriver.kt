package com.harman.interview.validation.email

import android.content.Context

/**
 * Email Valid Driver
 * @author vikramezhil
 */

class EmailValidDriver(context: Context): EmailValidEngine(context) {

    /**
     * Triggers the email validation
     * @param email The email to validate
     * @callback listener The class instance which implements the email valid listener
     */
    fun emailValidation(email: String, listener: EmailValidListener) {
        triggerEmailValidation(email, listener)
    }
}