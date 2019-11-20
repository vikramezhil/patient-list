package com.harman.interview.mvvm.viewmodel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.harman.interview.R
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun getFormValid() {
        onView(withId(R.id.et_email)).perform(typeText("abc@yahoo.com"))
        onView(withId(R.id.et_password)).perform(typeText("Password@123"))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText(R.string.loginAuthError)).check(doesNotExist())
    }

    @Test
    fun getSessionToken() {
        onView(withText(R.string.sessionExpired)).check(doesNotExist())
    }


}