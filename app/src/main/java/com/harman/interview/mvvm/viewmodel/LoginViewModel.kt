package com.harman.interview.mvvm.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.harman.interview.R
import com.harman.interview.mvvm.model.LoginResponse
import com.harman.interview.services.auth.email.EmailAuthDriver
import com.harman.interview.services.auth.email.EmailAuthListener
import com.harman.interview.utils.Storage
import com.harman.interview.validation.email.EmailValidDriver
import com.harman.interview.validation.email.EmailValidListener
import com.harman.interview.validation.password.PsswdValidDriver
import com.harman.interview.validation.password.PsswdValidListener
import retrofit2.Response

/**
 * Login View Model
 * @author vikramezhil
 */

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val emailDriver: EmailValidDriver
    private val passwordDriver: PsswdValidDriver
    private val emailAuthDriver: EmailAuthDriver

    var formValid: ObservableBoolean? = null

    var email: ObservableField<String>? = null
    var emailValid: ObservableBoolean? = null
    var emailMsg: ObservableField<String>? = null

    var password: ObservableField<String>? = null
    var passwordValid: ObservableBoolean? = null
    var passwordMsg: ObservableField<String>? = null

    var loader: ObservableBoolean? = null

    var sessionToken: MutableLiveData<String>? = null
    var toast: MutableLiveData<String>? = null
    var toggleKeyboard: MutableLiveData<Boolean>? = null

    init {
        emailDriver = EmailValidDriver(context)
        passwordDriver = PsswdValidDriver(context)
        emailAuthDriver = EmailAuthDriver(context)

        formValid = ObservableBoolean(true)

        email = ObservableField("")
        emailValid = ObservableBoolean(false)
        emailMsg = ObservableField(context.resources.getString(R.string.errEmailEmpty))

        password = ObservableField("")
        passwordValid = ObservableBoolean(false)
        passwordMsg = ObservableField(context.resources.getString(R.string.errPasswordEmpty))

        loader = ObservableBoolean(false)

        sessionToken = MutableLiveData<String>()
        toast = MutableLiveData<String>()
        toggleKeyboard = MutableLiveData<Boolean>()

        // Checking and sending a token update if already found
        sendTokenUpdate(Storage.retrieveFromSP(context, context.resources.getString(R.string.sessionKey)))
    }

    /**
     * Tracks the email
     * @param emailVal The email value
     * @param start The new count characters
     * @param before The old count characters
     * @param count The total characters count
     */
    fun trackEmail(emailVal: CharSequence, start: Int, before: Int, count: Int) {
        email?.set(emailVal.toString())
        emailDriver.emailValidation(emailVal.toString(), object: EmailValidListener {
            override fun onEmailValid(valid: Boolean, msg: String) {
                emailValid?.set(valid)
                emailMsg?.set(msg)
            }
        })
    }

    /**
     * Tracks the password
     * @param passwordVal The password value
     * @param start The new count characters
     * @param before The old count characters
     * @param count The total characters count
     */
    fun trackPassword(passwordVal: CharSequence, start: Int, before: Int, count: Int) {
        password?.set(passwordVal.toString())
        passwordDriver.passwordValidation(passwordVal.toString(), 8, object: PsswdValidListener {
            override fun onPasswordValid(valid: Boolean, msg: String) {
                passwordValid?.set(valid)
                passwordMsg?.set(msg)
            }
        })
    }

    /**
     * Triggers the login service
     */
    fun login() {
        formValid?.set(emailValid?.get()!! && passwordValid?.get()!!)
        if (formValid?.get()!!) {

            toggleKeyboard?.value = true

            // Form is valid, triggering the login service
            emailAuthDriver.emailAuthenticate(email?.get()!!, password?.get()!!, object: EmailAuthListener {
                override fun onEmailAuthInProgress(progress: Boolean) {
                    loader?.set(progress)
                }

                override fun onEmailAuthResponse(response: Response<LoginResponse>) {
                    val token = response.body()!!.access_token
                    Storage.storeInSP(context, context.resources.getString(R.string.sessionKey), token)
                    sendTokenUpdate(token)
                }

                override fun onEmailAuthError(errMsg: String) {
                    toast?.value = errMsg
                }
            })
        }
    }

    /**
     * Sends the token update
     * @param token The token to be sent
     */
    private fun sendTokenUpdate(token: String?) {
        token?.let {
            if(token.isNotEmpty()) {
                sessionToken?.value = token
            }
        }
    }
}