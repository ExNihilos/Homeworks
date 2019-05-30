package com.example.homework_1.Domain.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image (var id:Int =0 ,
                  var url:String? = "") : Parcelable