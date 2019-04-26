package com.example.homework_1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize

data class Card(var id:Int, var name: String, var category: Category?=null, var percent: Int, var images: MutableList<Image>?) : Parcelable
