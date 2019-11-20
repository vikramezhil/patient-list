package com.harman.interview.api

import com.harman.interview.mvvm.model.LoginRequest
import com.harman.interview.mvvm.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @Headers("Content-Type: application/json")
    @POST("androidtest/auth")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}