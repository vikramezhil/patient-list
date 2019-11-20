package com.harman.interview.services.patientslist

import android.content.Context

/**
 * Patients List Driver
 * @author vikramezhil
 */

class PatientsListDriver(context: Context): PatientsListEngine(context) {

    /**
     * Triggers the patients list service
     * @param token The authentication token
     * @callback listener The class which implements the patients list listListener
     */
    fun patientsList(token: String, listener: PatientsListListener) {
        triggerPatientsList(token, listener)
    }
}