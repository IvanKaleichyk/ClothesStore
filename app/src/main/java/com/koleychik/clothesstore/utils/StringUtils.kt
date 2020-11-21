package com.koleychik.clothesstore.utils

import android.content.Context

fun getStringFromResource(context: Context, res : Int) : String{
    return context.getString(res)
}

fun getListFromListResources(context: Context, listResource: List<Int>) : List<String>{
    val listResult = mutableListOf<String>()
    for (i in listResource){
        listResult.add(getStringFromResource(context, i))
    }
    return listResult
}