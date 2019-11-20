package com.harman.interview.api

import com.harman.interview.mvvm.model.PatientListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface PatientApi {

    @Headers("Content-Type: application/json")
    @GET("androidtest/patients")
    fun patients(@Header("token") token: String): Call<PatientListResponse>
}