package com.example.homework_1.Extensions

import com.example.homework_1.*
import com.example.homework_1.Realm.CardRealm
import io.realm.RealmList

fun CardRealm.map2Data() = Card(
    this.id,
    this.name,
    this.category?.map2Data(),
    this.percent,
    this.images?.mapToDataList()
)

fun Card.map2Realm(): CardRealm
{
    val cardRealm = CardRealm()
    cardRealm.id = this.id
    cardRealm.name = this.name
    cardRealm.category = this.category?.map2Realm()
    cardRealm.percent = this.percent
    cardRealm.images = this.images?.mapToRealmList()
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