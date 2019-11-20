package com.harman.interview.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.harman.interview.R
import com.harman.interview.databinding.ActivityPatientlistBinding
import com.harman.interview.mvvm.viewmodel.PatientListViewModel

/**
 * Patient List Activity
 * @author vikramezhil
 */

class PatientListActivity : AppCompatActivity() {

    private var binding: ActivityPatientlistBinding? = null
    var patientListVm: PatientListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patientlist)
        patientListVm = ViewModelProviders.of(this).get(PatientListViewModel::class.java)
        binding?.patientListVm = patientListVm
    }
}