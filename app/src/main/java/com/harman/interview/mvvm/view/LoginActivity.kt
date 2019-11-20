package com.harman.interview.mvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.harman.interview.R
import com.harman.interview.mvvm.viewmodel.LoginViewModel
import com.harman.interview.databinding.ActivityLoginBinding
import com.harman.interview.utils.toast
import com.harman.interview.utils.toggleKeyboard

/**
 * Login Activity
 * @author vikramezhil
 */

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    var loginVm: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginVm = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding?.loginVm = loginVm

        loginVm?.sessionToken?.observe(this, Observer { token ->
            // Starting the details activity once the token is retrieved
            val detailsIntent = Intent(this, PatientsListActivity::class.java)
            detailsIntent.putExtra(resources.getString(R.string.sessionKey), token)
            startActivity(detailsIntent)
            finish()
        })

        loginVm?.toggleKeyboard?.observe(this, Observer {
            this.toggleKeyboard()
        })

        loginVm?.toast?.observe(this, Observer { toastMsg ->
            toastMsg.toast(this)
        })
    }
}
