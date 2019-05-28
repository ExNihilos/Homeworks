package com.example.homework_1.Presentation.Card

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.homework_1.Domain.Models.Card
import com.example.homework_1.Providers.CardProvider
import com.example.homework_1.R
import kotlinx.android.synthetic.main.activity_card_list.*

class CardListActivity : AppCompatActivity(), CardAdapter.OnAdapterClickListener
{
    companion object {const val REQUEST_CODE = 1}

    var cardProvider = CardProvider()
    var cards = mutableListOf<Card>()
    //val adapter = CardAdapter(this,cards,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)
        cards = cardProvider.getCardsFromDB()
        if (cards.isNotEmpty())
        {
            Toast.makeText(this, "Карты загружены", LENGTH_SHORT).show()
            tvNoCard.visibility = View.INVISIBLE
            tvStartInfo.visibility = View.INVISIBLE
        }

        rvCard.layoutManager = LinearLayoutManager(this)
        rvCard.adapter= CardAdapter(this, cards, this)
    }


    fun AddNewCard_Click(view: View)
    {
        val intent1 = Intent(this, EditCardActivity::class.java)
        startActivityForResult(intent1, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
        {
            // var card = data?.getParcelableExtra(Card::class.java.simpleName) as Card
            // cards.add(card)
            // adapter.insertItem(card)
            // (rvCard.adapter as CardAdapter).notifyItemInserted(1)
            cards = cardProvider.getCardsFromDB()
            rvCard.adapter = CardAdapter(this, cards, this)
            tvNoCard.visibility = View.INVISIBLE
            tvStartInfo.visibility = View.INVISIBLE
        }
    }

    override fun onItemClick(position: Int, card: Card)
    {}

}

