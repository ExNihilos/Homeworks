package com.example.homework_1

import io.realm.RealmList

fun CategoryRealm.map2Data() = Category(this.ids,this.title)

fun Category.map2Realm(): CategoryRealm{
    val categoryRealm = CategoryRealm()
    categoryRealm.ids = this.id
    categoryRealm.title = "SS"//this.title!!
    return  categoryRealm
}

fun MutableList<Category>.map2RealmList(): RealmList<CategoryRealm> {
    val r1 = RealmList<CategoryRealm>()
    this.forEach{r1.add(it.map2Realm())}
    return r1
}

fun MutableList<CategoryRealm>.map2DataList(): MutableList<Category> {
    val l = ArrayList<Category>()
    this.forEach{l.add(it.map2Data())}
    return l
}