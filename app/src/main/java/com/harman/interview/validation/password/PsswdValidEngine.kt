package com.harman.interview.validation.password

import android.content.Context
import com.harman.interview.R
import com.harman.interview.utils.Validator

/**
 * Password Valid Engine
 * @author vikramezhil
 */

open class PsswdValidEngine(private val context: Context) {

    /**
     * Triggers the email validation
     * @param password The password to validate
     * @param min The minimum password length
     * @param listener The class instance which implements the password valid listener
     */
    protected fun triggerPasswordValidation(password: String, min: Int, listener: PsswdValidListener) {
        if(password.isEmpty()) {
            listener.onPasswordValid(false, context.resources.getString(R.string.errPasswordEmpty))
        } else if(!Validator.hasLowerCase(password)) {
            listener.onPasswordValid(false, context.resources.getString(R.string.errPasswordLowerCase))
        } else if(!Validator.hasUpperCase(password)) {
            listener.onPasswordValid(false, context.resources.getString(R.string.errPasswordUpperCase))
        } else if(!Validator.hasSpecialChar(password)) {
            listener.onPasswordValid(false, context.resources.getString(R.string.errPasswordSpecialChar))
        } else if(!Validator.hasNumeric(password)) {
            listener.onPasswordValid(false, context.resources.getString(R.string.errPasswordNumber))
        } else if(password.length < min) {
            listener.onPasswordValid(false, context.resources.getString(R.string.errPasswordMinLength).format(min))
        } else {
            listener.onPasswordValid(true, "")
        }
    }
}