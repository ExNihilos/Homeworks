package com.example.homework_1.Domain.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Card(var id:Int = 0,
                var name: String = "",
                var category: Category?=null,
                var percent: Int = 0,
                var images: MutableList<Image>? = null) : Parcelable
