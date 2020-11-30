package com.koleychik.clothesstore.repositories

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.koleychik.clothesstore.models.User
import com.koleychik.clothesstore.utils.constants.FirebaseConstants
import kotlinx.coroutines.launch


class FirebaseRepository {

    val database = FirebaseDatabase.getInstance()

    fun updateUser(name: String, imgUrl: Uri?, successful: () -> Unit,onFail: (text: String?) -> Unit) {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName("Jane Q. User")
            .setPhotoUri(imgUrl)
            .build()

        FirebaseAuth.getInstance().currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener {
                if (it.isSuccessful) successful()
                else onFail(it.exception!!.message)
            }
    }

    fun verifyEmail(user: FirebaseUser,successful: () -> Unit, failed: () -> Unit) {
        user.sendEmailVerification().addOnCompleteListener {
            if (it.isSuccessful) successful()
            else failed()
        }
    }

//    suspend fun putUserToDb(user: User, successful: () -> Unit, failed: (textRed: Int) -> Unit) {
//        database.getReference(FirebaseConstants.users)
//            .child(auth.currentUser!!.uid)
//            .setValue(user)
//    }


}