package com.harman.interview.mvvm.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel

/**
 * Patient List View Model
 * @author vikramezhil
 */

class PatientListViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    var resultsEmpty: ObservableBoolean? = null

    init {
        resultsEmpty = ObservableBoolean(false)
    }

    /**
     * Tracks the password
     * @param queryVal The query value
     * @param start The new count characters
     * @param before The old count characters
     * @param count The total characters count
     */
    fun trackFilterQuery(queryVal: CharSequence, start: Int, before: Int, count: Int) {

    }

}