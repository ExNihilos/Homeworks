package com.example.homework_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import io.realm.Realm
import io.realm.RealmModel

import kotlinx.android.synthetic.main.activity_card_list.*


class CardListActivity : AppCompatActivity(), CardAdapter.OnAdapterClickListener {

    companion object {
        const val REQUEST_CODE = 1
    }


    var cardProvider = CardProvider()
    //val card = Card(0,"", Category(1,""),0,ArrayList())
    var cards = mutableListOf<Card>()
    //val adapter = CardAdapter(this, cards,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)
        Realm.init(this)
        var cards = cardProvider.getCardsFromDB()
        if (cards.isNotEmpty())
        {
            Toast.makeText(this, "Карты загружены", LENGTH_SHORT).show()
            tvNoCard.visibility = View.INVISIBLE
            tvStartInfo.visibility = View.INVISIBLE
        }

        rvCard.layoutManager = LinearLayoutManager(this)
        rvCard.adapter = CardAdapter(this, cards, this)

      //  if (cards!=null)
        //{

       /*Realm.getDefaultInstance().use { realm ->
           val results = realm.where(CardRealm::class.java).findAll()
            cards = realm.copyFromRealm(results).map2DataList()

           {
            rvCard.adapter = CardAdapter(this, cards as ArrayList<Card>, this)

           }
           return realm.copyFromRealm(results).map2DataList()
       }
*/
         //   Toast.makeText(this, "Карты есть", LENGTH_SHORT).show()}
        //else Toast.makeText(this, "Карт нету", LENGTH_SHORT).show()
    }


    fun AddNewCard_Click(view: View) {
        val intent1 = Intent(this, EditCardActivity::class.java)
        startActivityForResult(intent1, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
        {
            var card = data?.getParcelableExtra(Card::class.java.simpleName) as Card
            cards.add(card)
            rvCard.adapter = CardAdapter(this, cards, this)
            tvNoCard.visibility = View.INVISIBLE
            tvStartInfo.visibility = View.INVISIBLE
        }
    }


    override fun onItemClick(position: Int, card: Card) {
        //val intent2221 = Intent(this, EditCardActivity::class.java)
        //finish()
    }

}

