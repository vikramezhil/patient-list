package com.harman.interview.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.harman.interview.R
import com.harman.interview.databinding.ActivityPatientdetailBinding
import com.harman.interview.mvvm.viewmodel.PatientDetailViewModel

/**
 * Patient Detail Activity
 * @author vikramezhil
 */

class PatientDetailActivity : AppCompatActivity() {

    private var binding: ActivityPatientdetailBinding? = null
    var patientDetailVm: PatientDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patientdetail)
        patientDetailVm = ViewModelProviders.of(this).get(PatientDetailViewModel::class.java)
        binding?.patientDetailVm = patientDetailVm
    }
}