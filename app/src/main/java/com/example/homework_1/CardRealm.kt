package com.example.homework_1

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CardRealm : RealmModel {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var category: CategoryRealm? = null
    var percent: Int = 0
    var images: RealmList<ImageRealm>? = null
}