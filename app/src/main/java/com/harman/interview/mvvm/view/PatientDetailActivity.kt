package com.harman.interview.mvvm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.harman.interview.R
import com.harman.interview.databinding.ActivityPatientdetailBinding
import com.harman.interview.mvvm.viewmodel.PatientDetailViewModel
import com.harman.interview.utils.toast

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

        val token = intent.getStringExtra(resources.getString(R.string.sessionKey))
        val patientID = intent.getIntExtra(resources.getString(R.string.patientIDKey), -1)
        token?.let {
            // Getting the patient details
            patientDetailVm?.getPatientDetail(token, patientID)
        }

        patientDetailVm?.toast?.observe(this, Observer { toastMsg ->
            toastMsg.toast(this)
        })

        patientDetailVm?.logoutUser?.observe(this, Observer {
            finish()
        })
    }
}