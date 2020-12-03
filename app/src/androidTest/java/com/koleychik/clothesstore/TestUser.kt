package com.koleychik.clothesstore

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.koleychik.clothesstore.models.User

class TestUser(context: Context) {
    val sPrefName = "sPrefName"

    val userEmailSave = "userTestSave"
    val userPasswordSave = "userPasswordSave"
    val basicEmail = "testemail@gmial.com"
    val basicPassword = "basicPassword"

    val mAuth = FirebaseAuth.getInstance()

    val sPref = context.getSharedPreferences(sPrefName, Context.MODE_PRIVATE)

    fun setEmail(value: String = basicEmail) {
        sPref.edit().putString(userEmailSave, value).apply()
    }

    fun setPassword(value: String= basicPassword) {
        sPref.edit().putString(userPasswordSave, value).apply()
    }

    fun getEmail() = sPref.getString(userEmailSave, basicEmail) ?: basicEmail

    fun getPassword() = sPref.getString(userPasswordSave, basicPassword) ?: basicPassword

    fun getUserModel(getUser: (user: FirebaseUser) -> Unit){
        val lastEmail = sPref.getString(userEmailSave, basicEmail)
        mAuth.signInWithEmailAndPassword(getEmail(), getPassword()).addOnCompleteListener {
            if (it.isSuccessful) getUser(mAuth.currentUser!!)
            else createUser(getUser)
        }
    }

    private fun createUser(getUser: (user: FirebaseUser) -> Unit) {
        mAuth.createUserWithEmailAndPassword(basicEmail, basicPassword).addOnCompleteListener {
            if (it.isSuccessful) getUser(mAuth.currentUser!!)
        }
    }
}