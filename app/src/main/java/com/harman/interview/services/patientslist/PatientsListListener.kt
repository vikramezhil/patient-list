package com.harman.interview.services.patientslist

import com.harman.interview.mvvm.model.PatientListResponse
import retrofit2.Response

interface PatientsListListener {
    /**
     * Sends an update on the patients search progress
     * @param progress The patients search running progress
     */
    fun onPatientsSearchInProgress(progress: Boolean)

    /**
     * Sends an update on the patients list response
     * @param response The patients list response
     */
    fun onPatientsResponse(response: Response<PatientListResponse>)

    /**
     * Sends an update on the patients error
     * @param errMsg The patients error message
     * @param sessionExpired The session expired status
     */
    fun onPatientsError(errMsg: String, sessionExpired: Boolean)
}