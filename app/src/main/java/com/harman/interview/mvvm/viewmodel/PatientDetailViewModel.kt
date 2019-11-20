package com.harman.interview.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class PatientDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext


}