package com.example.homework_1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize

data class Card(var id:Int, var name: String,  var category:@RawValue Category?=null, var percent: Int, var photos: ArrayList<String>) : Parcelable
