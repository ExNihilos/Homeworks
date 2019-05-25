package com.example.homework_1.Domain.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Category(var id:Int =0,
                    var title: String = "Категория"): Parcelable

