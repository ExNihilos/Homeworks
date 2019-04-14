package com.example.homework_1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CardAdapter (context: Context, var cards: ArrayList<Card>, private var listener: OnAdapterClickListener?) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>()
{

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.itemView.tag = cards[position]
        holder.cardname.text = cards[position].name
        holder.category.text = cards[position].category
        holder.percent.text = "Скидка "+cards[position].percent.toString() + "%"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_card, parent, false)
        return CardViewHolder(view, listener)
    }


    override fun getItemCount(): Int
    {
        return cards.size
    }

    fun insertItem(item : Card)
    {
        cards.add(1,item)
        notifyItemInserted(1)
    }

    class CardViewHolder(itemView: View, private val listener: OnAdapterClickListener?) :
        RecyclerView.ViewHolder(itemView),

        View.OnClickListener
    {
        var cardname: TextView = itemView!!.findViewById(R.id.tvCardName)
        var category: TextView = itemView!!.findViewById(R.id.tvCategory)
        var percent: TextView = itemView!!.findViewById(R.id.tvPercent)

        init {
            itemView!!.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            listener?.onItemClick(adapterPosition, itemView.tag as Card)
        }
    }


    interface OnAdapterClickListener
    {
        fun onItemClick(position: Int, card: Card)
    }

}