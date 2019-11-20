package com.harman.interview.services.patientdetail

import android.content.Context
import com.harman.interview.R
import com.harman.interview.api.PatientApi
import com.harman.interview.mvvm.model.PatientDetailResponse
import com.harman.interview.services.WebService
import com.harman.interview.utils.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Patient Detail Engine
 * @author vikramezhil
 */

open class PatientDetailEngine(private val context: Context) {

    /**
     * Triggers the patient detail service
     * @param token The authentication token
     * @param patientID The patient ID
     * @callback listener The class instance which implements the patient detail listener
     */
    protected fun triggerPatientDetail(token: String, patientID: Int, listener: PatientDetailListener) {
        listener.onPatientDetailInProgress(true)
        WebService.client.create(PatientApi::class.java).patientDetail(token, patientID.toString()).enqueue(object: Callback<PatientDetailResponse> {
            override fun onResponse(call: Call<PatientDetailResponse>, response: Response<PatientDetailResponse>) {
                listener.onPatientDetailInProgress(false)
                if (response.isSuccessful && response.body() != null) {
                    listener.onPatientDetailResponse(response)
                } else if (response.code() == 401) {
                    listener.onPatientDetailError(context.resources.getString(R.string.sessionExpired), true)
                } else {
                    listener.onPatientDetailError(context.resources.getString(R.string.patientDetailFetchError), false)
                }
            }

            override fun onFailure(call: Call<PatientDetailResponse>, t: Throwable) {
                listener.onPatientDetailInProgress(false)
                if(Network.isAvailable(context)) {
                    listener.onPatientDetailError(context.resources.getString(R.string.patientDetailFetchError), false)
                } else {
                    listener.onPatientDetailError(context.resources.getString(R.string.networkError), false)
                }
            }
        })
    }
}