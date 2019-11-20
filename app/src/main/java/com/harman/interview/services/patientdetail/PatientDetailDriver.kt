package com.harman.interview.services.patientdetail

import android.content.Context

/**
 * Patient Detail Driver
 * @author vikramezhil
 */

class PatientDetailDriver(context: Context): PatientDetailEngine(context) {

    /**
     * Triggers the patient detail service
     * @param token The authentication token
     * @param patientID The patient ID
     * @callback listener The class instance which implements the patient detail listener
     */
    fun patientDetail(token: String, patientID: Int, listener: PatientDetailListener) {
        triggerPatientDetail(token, patientID, listener)
    }
}