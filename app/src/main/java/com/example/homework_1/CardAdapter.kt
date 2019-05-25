package com.example.homework_1

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.homework_1.Domain.Models.Card


class CardAdapter(val context: Context, var cards: MutableList<Card>, private var listener: OnAdapterClickListener?) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onBindViewHolder(holder: CardViewHolder, position: Int)
    {
        holder.itemView.tag = cards[position]
        holder.cardname.text = cards[position].name
        holder.category.text = cards[position].category?.title
        holder.percent.text = "Скидка ${cards[position].percent}%"
        holder.rvCardImage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.rvCardImage.adapter = CardPhotoAdapter(cards[position].images!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder
    {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_card, parent, false)
        return CardViewHolder(view, listener)
    }


    override fun getItemCount(): Int
    {
        return cards.size
    }

    fun insertItem(item: Card)
    {
        cards.add(0, item)
        notifyItemInserted(0)
    }


    class CardViewHolder(itemView: View, private val listener: OnAdapterClickListener?) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var cardname: TextView = itemView.findViewById(R.id.tvCardName)
        var category: TextView = itemView.findViewById(R.id.tvCategory)
        var percent: TextView = itemView.findViewById(R.id.tvPercent)
        var rvCardImage: RecyclerView = itemView.findViewById(R.id.rvCardImage)

        init
        {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?)
        {
            listener?.onItemClick(adapterPosition, itemView.tag as Card)
        }
    }

    interface OnAdapterClickListener
    {
        fun onItemClick(position: Int, card: Card)
    }



}