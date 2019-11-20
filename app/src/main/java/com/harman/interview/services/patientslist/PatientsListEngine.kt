package com.harman.interview.services.patientslist

import android.content.Context
import com.harman.interview.R
import com.harman.interview.api.PatientApi
import com.harman.interview.mvvm.model.PatientListResponse
import com.harman.interview.services.WebService
import com.harman.interview.utils.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Patients List Engine
 * @author vikramezhil
 */

open class PatientsListEngine(private val context: Context) {

    /**
     * Triggers the patients list service
     * @param token The authentication token
     * @callback listener The class which implements the patients list listListener
     */
    protected fun triggerPatientsList(token: String, listener: PatientsListListener) {
        listener.onPatientsSearchInProgress(true)
        WebService.client.create(PatientApi::class.java).patients(token).enqueue(object: Callback<PatientListResponse> {
            override fun onResponse(call: Call<PatientListResponse>, response: Response<PatientListResponse>) {
                listener.onPatientsSearchInProgress(false)
                if (response.isSuccessful && response.body() != null) {
                    listener.onPatientsResponse(response)
                } else if (response.code() == 401) {
                    listener.onPatientsError(context.resources.getString(R.string.sessionExpired), true)
                } else {
                    listener.onPatientsError(context.resources.getString(R.string.patientsFetchError), false)
                }
            }

            override fun onFailure(call: Call<PatientListResponse>, t: Throwable) {
                listener.onPatientsSearchInProgress(false)
                if(Network.isAvailable(context)) {
                    listener.onPatientsError(context.resources.getString(R.string.patientsFetchError), false)
                } else {
                    listener.onPatientsError(context.resources.getString(R.string.networkError), false)
                }
            }
        })
    }
}