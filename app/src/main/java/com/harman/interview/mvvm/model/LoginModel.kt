package com.harman.interview.mvvm.model

import com.google.gson.annotations.SerializedName

class LoginRequest(email: String, psswd: String) {
    @SerializedName("EmailAddress")
    var emailAddress = email

    @SerializedName("Password")
    var password = psswd
}

data class LoginResponse(val access_token: String) {}