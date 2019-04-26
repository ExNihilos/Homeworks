package com.example.homework_1

import android.content.Context
import io.realm.Realm

class CardProvider
{
    lateinit var cards: MutableList<Card>
    val imageProvider: ImageProvider
    val categoryProvider: CategoryProvider

    init {
        imageProvider = ImageProvider()
        categoryProvider = CategoryProvider()
    }

    fun initConnection (context: Context)
    {
        cards = mutableListOf(
            Card(0, "Card1", categoryProvider.categories[0], 5, ArrayList()),
            Card(1, "Card2", categoryProvider.categories[1], 15, ArrayList())
        )
    }

    fun getCardsFromDB(): MutableList<Card>
    {
        Realm.getDefaultInstance().use { realm ->
            val result = realm
                .where(CardRealm::class.java)
                .findAll()
            return realm.copyFromRealm(result).map2DataList()
        }
    }

    fun getId(): Long
    {
        Realm.getDefaultInstance().use { realm ->
            val result = realm
                .where(CardRealm::class.java).count()

            return result
        }
    }

    fun saveCardToDB(card: Card)
    {
        Realm.getDefaultInstance().use { realm->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(card.map2Realm()))
            realm.commitTransaction()
        }
    }
}