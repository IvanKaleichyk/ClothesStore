package com.koleychik.clothesstore.ui.states

import android.annotation.SuppressLint
import android.os.Parcelable
import com.koleychik.clothesstore.models.HistoryModel
import kotlinx.android.parcel.Parcelize

@Parcelize
open class SearchState : Parcelable {
    @SuppressLint("ParcelCreator")
    object Loading : SearchState()
    @SuppressLint("ParcelCreator")
    class ShowResult(val list: List<HistoryModel>) : SearchState()
}