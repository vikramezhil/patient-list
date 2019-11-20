package com.harman.interview.mvvm.model

import com.google.gson.annotations.SerializedName

data class PatientDetailResponse(@SerializedName("Id") val id: Int, @SerializedName("FirstName") val firstName: String,
                                 @SerializedName("LastName") val lastName: String, @SerializedName("Address") val address: String,
                                 @SerializedName("NhsNumber") val nhsNumber: String) {}