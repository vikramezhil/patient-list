package com.harman.interview.validation.password

import android.content.Context

/**
 * Password Valid Driver
 * @author vikramezhil
 */

class PsswdValidDriver(context: Context): PsswdValidEngine(context) {

    /**
     * Triggers the email validation
     * @param password The password to validate
     * @param min The minimum password length
     * @param listener The class instance which implements the password valid listener
     */
    fun passwordValidation(password: String, min: Int, listener: PsswdValidListener) {
        triggerPasswordValidation(password, min, listener)
    }
}