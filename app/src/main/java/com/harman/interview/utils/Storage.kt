package com.harman.interview.utils

import android.content.Context
import com.harman.interview.R

/**
 * Storage Utilities
 * @author vikramezhil
 */

class Storage {

    companion object {
        /**
         * Stores data in shared preference
         * @param context The application context
         * @param key The data key
         * @param value The data value
         */
        fun storeInSP(context: Context, key: String, value: String) {
            val preference = context.getSharedPreferences(context.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
            val editor = preference.edit()
            editor.putString(key, value)
            editor.apply()
        }

        /**
         * Retrieves data from shared preference
         * @param context The application context
         * @param key The data key
         * @return The data value based on the key
         */
        fun retrieveFromSP(context: Context, key: String): String? {
            val preference = context.getSharedPreferences(context.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
            return preference.getString(key, "")
        }

        /**
         * Deletes data from shared preference
         * @param context The application context
         * @param key The data key
         */
        fun deleteFromSP(context: Context, key: String) {
            val preference = context.getSharedPreferences(context.resources.getString(R.string.app_name), Context.MODE_PRIVATE)
            val editor = preference.edit()
            editor.remove(key)
            editor.apply()
        }
    }
}