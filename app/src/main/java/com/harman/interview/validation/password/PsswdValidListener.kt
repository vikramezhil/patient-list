package com.harman.interview.validation.password

interface PsswdValidListener {
    /**
     * Sends an update on the password valid status
     * @param valid The password valid status
     * @param msg The msg to show to user if any
     */
    fun onPasswordValid(valid: Boolean, msg: String)
}