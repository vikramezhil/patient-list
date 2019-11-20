package com.harman.interview.utils

import java.util.regex.Pattern

/**
 * Validator Utilities
 * @author vikramezhil
 */

class Validator {

    companion object {
        /**
         * Checks if the email is valid
         * @param value The email value to check
         * @return The email valid status
         */
        fun isEmailValid(value: String): Boolean {
            val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(value)
            return matcher.matches()
        }

        /**
         * Checks if the value has upper case
         * @param value The value to check
         * @return The value has upper case status
         */
        fun hasUpperCase(value: String): Boolean {
            val expression = "(.*[A-Z].*)"
            val pattern = Pattern.compile(expression)
            val matcher = pattern.matcher(value)
            return matcher.matches()
        }

        /**
         * Checks if the value has lower case
         * @param value The value to check
         * @return The value has lower case status
         */
        fun hasLowerCase(value: String): Boolean {
            val expression = "(.*[a-z].*)"
            val pattern = Pattern.compile(expression)
            val matcher = pattern.matcher(value)
            return matcher.matches()
        }

        /**
         * Checks if the value has special character
         * @param value The value to check
         * @return The value has special character status
         */
        fun hasSpecialChar(value: String): Boolean {
            val expression = "[a-zA-Z0-9]*"
            val pattern = Pattern.compile(expression)
            val matcher = pattern.matcher(value)
            return !matcher.matches()
        }


        /**
         * Checks if the value has numeric
         * @param value The value to check
         * @return The value has numeric status
         */
        fun hasNumeric(value: String): Boolean {
            val expression = "(.*\\d.*)"
            val pattern = Pattern.compile(expression)
            val matcher = pattern.matcher(value)
            return matcher.matches()
        }
    }
}