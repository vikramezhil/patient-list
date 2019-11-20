package com.harman.interview.mvvm.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class PatientListResponse(@SerializedName("patients") val patients: ArrayList<PatientData>) {}

data class PatientData(@SerializedName("Id") val id: Int, @SerializedName("FirstName") val firstName: String) {}