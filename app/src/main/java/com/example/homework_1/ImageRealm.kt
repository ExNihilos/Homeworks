package com.example.homework_1

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ImageRealm : RealmModel
{
    @PrimaryKey
    var id: Int = 0
    var url: String? = ""
}