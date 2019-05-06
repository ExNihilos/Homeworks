package com.example.homework_1.Providers

import android.content.Context
import com.example.homework_1.Card
import com.example.homework_1.Realm.CardRealm
import com.example.homework_1.Extensions.map2DataList
import com.example.homework_1.Extensions.map2Realm
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

    fun getId(): Int?
    {
        Realm.getDefaultInstance().use { realm ->
            val result = realm
                .where(CardRealm::class.java)
                .findAll()
            val last = result.lastOrNull()
            return last?.id
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