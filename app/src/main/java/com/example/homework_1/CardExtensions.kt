package com.example.homework_1

import io.realm.RealmList

fun CardRealm.map2Data() = Card(this.id,this.name,this.category?.map2Data(),this.percent, this.photos as ArrayList<String>)

fun Card.map2Realm(): CardRealm
{
    val cardRealm = CardRealm()
    cardRealm.id = this.id
    cardRealm.name = this.name
    cardRealm.category = this.category?.map2Realm()
    cardRealm.percent = this.percent
    //cardRealm.photos = this.photos?.map2RealmList()
    return  cardRealm
}

fun MutableList<Card>.map2RealmList(): RealmList<CardRealm>
{
    val r1 = RealmList<CardRealm>()
    this.forEach{r1.add(it.map2Realm())}
    return r1
}



fun MutableList<CardRealm>.map2DataList(): ArrayList<Card>
{
    val l = ArrayList<Card>()
    this.forEach{l.add(it.map2Data())}
    return l
}