package com.harman.interview.api

import com.harman.interview.mvvm.model.PatientListResponse
import com.harman.interview.mvvm.model.PatientDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface PatientApi {

    @Headers("Content-Type: application/json")
    @GET("androidtest/patients")
    fun patients(@Header("token") token: String): Call<PatientListResponse>

    @Headers("Content-Type: application/json")
    @GET("androidtest/patient/{patient_id}")
    fun patientDetail(@Header("token") token: String, @Path("patient_id") patientId: String): Call<PatientDetailResponse>
}