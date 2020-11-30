package com.koleychik.clothesstore.utils

import com.koleychik.clothesstore.R

class SignUtils {

    companion object {
        private const val passwordMax = 12
        private const val passwordMin = 5
        private val listWrongChars =
            listOf('@', '$', '%', '^', '&', '*', '?', '/', '(', ')', '!', ';')

        fun checkPassword(value: String): Int? {
            val arr = value.toCharArray()
            if (arr.size > passwordMax) return R.string.tooLongPassword
            if (arr.size < passwordMin) return R.string.tooShortPassword
            for (i in listWrongChars) if (arr.contains(i)) return R.string.passwordContainsWrongCharacter
            return null
        }

        fun checkingEmail(value: String): Int? {
            val arr = value.toCharArray()
            if(arr.isEmpty()) return R.string.emptyField
            if (!arr.contains('@') || !arr.contains('.')) return R.string.emailField
            return null
        }

        fun isPasswordMatch(value1: String, value2: String): Int? {
            if (value1 != value2) return R.string.passwordNotMatch
            return null
        }
    }

}