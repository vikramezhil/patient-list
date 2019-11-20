package com.harman.interview.mvvm.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harman.interview.R
import com.harman.interview.mvvm.model.PatientData
import com.harman.interview.mvvm.model.PatientListResponse
import kotlinx.android.synthetic.main.rv_patients.view.*

/**
 * Patients List Adapter
 * @author vikramezhil
 */

interface PatientClickListener {
    /**
     * Sends an update on the patient clicked ID
     * @param patientID The patient ID
     */
    fun onPatientClickedID(patientID: Int)
}

class PatientsListAdapter(private val patientsData: PatientListResponse, private val listener: PatientClickListener): RecyclerView.Adapter<PatientsListAdapter.PatientsHolderView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsHolderView {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.rv_patients, parent, false)
        return PatientsHolderView(inflatedView, listener)
    }

    override fun onBindViewHolder(holder: PatientsHolderView, position: Int) {
        holder.bindPatients(patientsData.patients[position], position)
    }

    override fun getItemCount(): Int {
        return patientsData.patients.size
    }

    class PatientsHolderView(v: View, private var listener: PatientClickListener): RecyclerView.ViewHolder(v) {
        private var view : View = v

        /**
         * Binds the patients data
         * @param patient The patient data
         * @param position The position of the patient data
         */
        fun bindPatients(patient: PatientData, position: Int) {
            view.tag = position
            view.tv_patientName.text = patient.firstName
            view.tv_patientID.text = patient.id.toString()
            view.setOnClickListener { v -> listener.onPatientClickedID(patient.id) }
        }
    }
}