package com.harman.interview.mvvm.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.harman.interview.R
import com.harman.interview.mvvm.model.PatientDetailResponse
import com.harman.interview.services.patientdetail.PatientDetailDriver
import com.harman.interview.services.patientdetail.PatientDetailListener
import com.harman.interview.utils.Storage
import retrofit2.Response

/**
 * Patient Detail View Model
 * @author vikramezhil
 */

class PatientDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val patientDetailDriver: PatientDetailDriver

    var resultsEmpty: ObservableBoolean? = null
    var searchInProgress: ObservableBoolean? = null
    var searchError: ObservableField<String>? = null

    var toast: MutableLiveData<String>? = null
    var patientDetail: ObservableField<PatientDetailResponse>? = null
    var logoutUser: MutableLiveData<Boolean>? = null

    init {
        patientDetailDriver = PatientDetailDriver(context)

        resultsEmpty = ObservableBoolean(false)
        searchInProgress = ObservableBoolean(false)
        searchError = ObservableField("")

        toast = MutableLiveData<String>()
        patientDetail = ObservableField<PatientDetailResponse>()
        logoutUser = MutableLiveData<Boolean>()
    }

    /**
     * Deletes the token
     */
    private fun deleteToken() {
        Storage.deleteFromSP(context, context.resources.getString(R.string.sessionKey))
    }

    /**
     * Gets the patient details
     * @param token The authentication token
     * @param patientID The patient ID
     */
    fun getPatientDetail(token: String, patientID: Int) {
        if (token.isNotEmpty() && patientID != -1) {
            patientDetailDriver.patientDetail(token, patientID, object: PatientDetailListener {
                override fun onPatientDetailInProgress(progress: Boolean) {
                    searchInProgress?.set(progress)
                }

                override fun onPatientDetailResponse(response: Response<PatientDetailResponse>) {
                    resultsEmpty?.set(false)
                    patientDetail?.set(response.body())
                }

                override fun onPatientDetailError(errMsg: String, sessionExpired: Boolean) {
                    searchError?.set(errMsg)
                    resultsEmpty?.set(true)

                    if (sessionExpired) {
                        toast?.value = errMsg
                        logoutUser?.value = true
                        deleteToken()
                    }
                }
            })
        }
    }
}