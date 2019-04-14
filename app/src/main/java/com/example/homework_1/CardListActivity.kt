package com.example.homework_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.homework_1.EditCardActivty.Companion.REQUEST_CODE
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.activity_card_list.view.*
import kotlinx.android.synthetic.main.item_view_card.*


class CardListActivity : AppCompatActivity(), CardAdapter.OnAdapterClickListener  {

    companion object { const val REQUEST_CODE = 1 }
    val cards: ArrayList<Card> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)
        /*for (i in 1..5)
        {
            cards.add(Card("Карта$i","Категория", 10))
        }*/
        rvCard.layoutManager = LinearLayoutManager(this)
        rvCard.adapter = CardAdapter(this, cards, this)

    }


    fun AddNewCard_Click(view: View)
    {
        val intent1 = Intent(this, EditCardActivty::class.java)
        startActivityForResult(intent1, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            var card = data?.getParcelableExtra(Card::class.java.simpleName) as Card
            cards.add(card)
            rvCard.adapter = CardAdapter(this, cards, this)
            tvNoCard.visibility = View.INVISIBLE
            tvStartInfo.visibility = View.INVISIBLE
            /*tvCardName.text = card.name
            tvCategory.text = card.category
            tvPercent.text = "Скидка ${card.percent}%"
            tvCardName.visibility = View.VISIBLE
            tvCategory.visibility = View.VISIBLE
            tvPercent.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE*/

        }
    }


    override fun onItemClick(position: Int, card: Card)
    {
        val intent2221 = Intent(this, EditCardActivty::class.java)
        finish()
    }

}

