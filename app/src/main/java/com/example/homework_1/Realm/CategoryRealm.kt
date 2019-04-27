package com.example.homework_1.Realm

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CategoryRealm : RealmModel { // RealmObject?
    @PrimaryKey
    var ids: Int = 0
    var title: String = ""
   // var categorylist = mutableListOf<Category>()
}