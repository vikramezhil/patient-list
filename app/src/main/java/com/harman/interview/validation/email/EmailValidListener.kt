package com.harman.interview.validation.email

interface EmailValidListener {
    /**
     * Sends an update on the email valid status
     * @param valid The email valid status
     * @param msg The msg to show to user if any
     */
    fun onEmailValid(valid: Boolean, msg: String)
}