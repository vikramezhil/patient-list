package com.harman.interview.mvvm.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.harman.interview.R
import com.harman.interview.mvvm.model.PatientListResponse
import com.harman.interview.services.patientslist.PatientsListDriver
import com.harman.interview.services.patientslist.PatientsListListener
import com.harman.interview.utils.Storage
import retrofit2.Response

/**
 * Patient List View Model
 * @author vikramezhil
 */

class PatientsListViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val patientsListDriver: PatientsListDriver
    private var token: String? = null

    var resultsEmpty: ObservableBoolean? = null
    var searchInProgress: ObservableBoolean? = null
    var searchError: ObservableField<String>? = null

    var toast: MutableLiveData<String>? = null
    var patientsList: MutableLiveData<PatientListResponse>? = null
    var logoutUser: MutableLiveData<Boolean>? = null

    var mainPatientList: PatientListResponse
    var filterPatientList: PatientListResponse

    init {
        patientsListDriver = PatientsListDriver(context)

        resultsEmpty = ObservableBoolean(false)
        searchInProgress = ObservableBoolean(false)
        searchError = ObservableField("")

        toast = MutableLiveData<String>()
        patientsList = MutableLiveData<PatientListResponse>()
        logoutUser = MutableLiveData<Boolean>()

        mainPatientList = PatientListResponse(ArrayList())
        filterPatientList = mainPatientList

        retrieveToken()
    }

    /**
     * Retrieves the token
     */
    private fun retrieveToken() {
        val tokenVal = Storage.retrieveFromSP(context, context.resources.getString(R.string.sessionKey))
        tokenVal?.let {
            if(tokenVal.isNotEmpty()) {
                token = tokenVal
                getPatients()
            }
        }
    }


    /**
     * Deletes the token
     */
    private fun deleteToken() {
        Storage.deleteFromSP(context, context.resources.getString(R.string.sessionKey))
    }

    /**
     * Tracks the filter query
     * @param queryVal The query value
     * @param start The new count characters
     * @param before The old count characters
     * @param count The total characters count
     */
    fun trackFilterQuery(queryVal: CharSequence, start: Int, before: Int, count: Int) {
        if (queryVal.isEmpty()) {
            // Resetting the live data with the original results
            patientsList?.value = mainPatientList
        } else {
            filterPatientList = PatientListResponse(ArrayList())
            for (patient in mainPatientList.patients) {
                if (patient.firstName.startsWith(queryVal.toString(), true) || patient.id.toString().startsWith(queryVal.toString())) {
                    filterPatientList.patients.add(patient)
                }
            }

            // Updating the live data with the filtered results
            patientsList?.value = filterPatientList
        }
    }

    /**
     * Gets the patients list
     */
    fun getPatients() {
        token?.let {
            if(token!!.isNotEmpty()) {
                resetFilters()
                patientsListDriver.patientsList(token!!, object: PatientsListListener {
                    override fun onPatientsSearchInProgress(progress: Boolean) {
                        searchInProgress?.set(progress)
                    }

                    override fun onPatientsResponse(response: Response<PatientListResponse>) {
                        resultsEmpty?.set(false)
                        patientsList?.value = response.body()
                        mainPatientList = response.body()!!
                    }

                    override fun onPatientsError(errMsg: String, sessionExpired: Boolean) {
                        resultsEmpty?.set(true)
                        searchError?.set(errMsg + context.resources.getString(R.string.swipeToRefresh))

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

    /**
     * Resets the filers data
     */
    private fun resetFilters() {
        mainPatientList = PatientListResponse(ArrayList())
        filterPatientList = mainPatientList
    }
}