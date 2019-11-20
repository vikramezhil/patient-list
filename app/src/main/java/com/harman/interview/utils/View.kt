package com.harman.interview.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * View Extensions
 * @author vikramezhil
 */


/**
 * Toast
 * @param context The application context
 */
fun String.toast(context: Context): Toast {
    return Toast.makeText(context, this, Toast.LENGTH_SHORT).apply { show() }
}

/**
 * Toggles the soft keyboard
 */
fun Context.toggleKeyboard() {
    val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(0, 0)
}
