package com.example.homework_1

import io.realm.RealmList

fun CardRealm.map2Data() = Card(this.id,this.name,this.category,this.percent, this.photos)

fun Card.map2Realm(): CardRealm
{
    val cardRealm = CardRealm()
    cardRealm.id = this.id
    cardRealm.name = this.name
    cardRealm.category = this.category
    cardRealm.percent = this.percent
    cardRealm.photos = this.photos
    return  cardRealm
}

fun MutableList<Card>.map2RealmList(): RealmList<CardRealm>
{
    val r1 = RealmList<CardRealm>()
    this.forEach{r1.add(it.map2Realm())}
    return r1
}

fun MutableList<CardRealm>.map2DataList(): MutableList<Card>
{
    val l = ArrayList<Card>()
    this.forEach{l.add(it.map2Data())}
    return l
}