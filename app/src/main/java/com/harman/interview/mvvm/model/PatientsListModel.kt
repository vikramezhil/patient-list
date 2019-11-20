package com.harman.interview.mvvm.model

import com.google.gson.annotations.SerializedName

data class PatientListResponse(@SerializedName("patients") val patients: List<PatientData>) {}

data class PatientData(@SerializedName("Id") val id: Int, @SerializedName("FirstName") val firstName: String) {}