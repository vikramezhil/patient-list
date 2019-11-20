package com.harman.interview.validation.email

import android.content.Context
import com.harman.interview.R
import com.harman.interview.utils.Validator

/**
 * Email Valid Engine
 * @author vikramezhil
 */

open class EmailValidEngine(private val context: Context) {

    /**
     * Triggers the email validation
     * @param email The email to validate
     * @param listener The class instance which implements the email valid listener
     */
    protected fun triggerEmailValidation(email: String, listener: EmailValidListener) {
        if (email.isEmpty()) {
            listener.onEmailValid(false, context.resources.getString(R.string.errEmailEmpty))
        } else if (!Validator.isEmailValid(email)) {
            listener.onEmailValid(false, context.resources.getString(R.string.errEmailInvalid))
        } else {
            listener.onEmailValid(true, "")
        }
    }
}