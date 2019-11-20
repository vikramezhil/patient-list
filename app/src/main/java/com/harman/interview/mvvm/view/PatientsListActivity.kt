package com.harman.interview.mvvm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.harman.interview.R
import com.harman.interview.databinding.ActivityPatientlistBinding
import com.harman.interview.mvvm.presenter.PatientClickListener
import com.harman.interview.mvvm.presenter.PatientsListAdapter
import com.harman.interview.mvvm.viewmodel.PatientsListViewModel
import com.harman.interview.utils.toast

/**
 * Patient List Activity
 * @author vikramezhil
 */

class PatientsListActivity : AppCompatActivity() {

    private var binding: ActivityPatientlistBinding? = null
    var patientsListVm: PatientsListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patientlist)
        patientsListVm = ViewModelProviders.of(this).get(PatientsListViewModel::class.java)
        binding?.patientListVm = patientsListVm

        patientsListVm?.patientsList?.observe(this, Observer { patientsList ->
            binding!!.rvDetails.adapter = PatientsListAdapter(patientsList, object: PatientClickListener {
                override fun onPatientClickedID(patientID: Int) {
                    showPatientDetailsScreen(patientID)
                }
            })
        })

        patientsListVm?.toast?.observe(this, Observer { toastMsg ->
            toastMsg.toast(this)
        })

        patientsListVm?.logoutUser?.observe(this, Observer {
            showLoginScreen()
        })
    }

    /**
     * Shows the login screen
     */
    private fun showLoginScreen() {
        val loginIntent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }

    /**
     * Shows the patient detail screen
     * @param patientID The patient ID
     */
    private fun showPatientDetailsScreen(patientID: Int) {
        val patientDetailIntent = Intent(applicationContext, PatientDetailActivity::class.java)
        patientDetailIntent.putExtra(resources.getString(R.string.sessionKey), intent.getStringExtra(resources.getString(R.string.sessionKey)))
        patientDetailIntent.putExtra(resources.getString(R.string.patientIDKey), patientID)
        startActivity(patientDetailIntent)
    }
}