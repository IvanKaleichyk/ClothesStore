package com.koleychik.clothesstore.repositories

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.koleychik.clothesstore.models.DeviceImage
import javax.inject.Inject

class DeviceImagesRepository @Inject constructor(private val context: Context) {

    fun getAll(): MutableList<DeviceImage>? {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
        )

        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

        if (cursor != null) {
            val list = mutableListOf<DeviceImage>()
            while (cursor.moveToNext()) {
                list.add(0,
                    DeviceImage(
                        id = cursor.getString(0),
                        data = cursor.getString(1),
                    )
                )
            }
            cursor.close()
            return list
        }
        return null
    }

}