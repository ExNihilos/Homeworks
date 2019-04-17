package com.example.homework_1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Card(var name: String, var category: String, var percent: Int, var photos: ArrayList<String>) : Parcelable
