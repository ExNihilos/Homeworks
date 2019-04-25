package com.example.homework_1

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CardRealm : RealmModel
{
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var category: Category? = null
    var percent: Int = 0
    var photos: ArrayList<String> = ArrayList()
    //var categorylist = mutableListOf<Category>()

}