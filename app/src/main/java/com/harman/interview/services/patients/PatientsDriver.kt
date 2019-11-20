package com.harman.interview.services.patients

import android.content.Context

/**
 * Patients Driver
 * @author vikramezhil
 */

class PatientsDriver(context: Context): PatientsEngine(context) {

    /**
     * Triggers the patient details service
     * @param token The authentication token
     * @callback listener The class which implements the patients listener
     */
    fun patientDetails(token: String, listener: PatientsListener) {
        triggerPatientDetails(token, listener)
    }
}