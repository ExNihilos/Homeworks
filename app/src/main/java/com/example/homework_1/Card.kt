package com.example.homework_1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Card(var id:Int,
                var name: String,
                var category: Category?=null,
                var percent: Int,
                var images: MutableList<Image>?) : Parcelable
