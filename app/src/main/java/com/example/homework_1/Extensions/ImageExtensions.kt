package com.example.homework_1.Extensions

import com.example.homework_1.Domain.Models.Image
import com.example.homework_1.Realm.ImageRealm
import io.realm.RealmList

fun Image.mapToRealm(): ImageRealm
{
    val imageRealm = ImageRealm()
    imageRealm.id = this.id
    imageRealm.url = this.url
    return imageRealm
}

fun ImageRealm.mapToData() = Image(this.id, this.url!!)

fun MutableList<Image>.mapToRealmList(): RealmList<ImageRealm>
{
    val rl = RealmList<ImageRealm>()
    this.forEach { rl.add(it.mapToRealm()) }
    return rl
}

fun MutableList<ImageRealm>.mapToDataList(): MutableList<Image>
{
    val l = ArrayList<Image>()
    this.forEach { l.add(it.mapToData()) }
    return l
}