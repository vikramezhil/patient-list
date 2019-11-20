package com.harman.interview.services.patientdetail

import com.harman.interview.mvvm.model.PatientDetailResponse
import retrofit2.Response

interface PatientDetailListener {
    /**
     * Sends an update on the patient detail running progress
     * @param progress The patients detail running progress
     */
    fun onPatientDetailInProgress(progress: Boolean)

    /**
     * Sends an update on the patient detail response
     * @param response The patientt detail response
     */
    fun onPatientDetailResponse(response: Response<PatientDetailResponse>)

    /**
     * Sends an update on the patient detail error
     * @param errMsg The patients detail error message
     * @param sessionExpired The session expired status
     */
    fun onPatientDetailError(errMsg: String, sessionExpired: Boolean)
}