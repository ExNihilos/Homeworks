package com.example.homework_1

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class CardPhotoAdapter(var photos: ArrayList<String>) : RecyclerView.Adapter<CardPhotoAdapter.CardPhotoViewHolder>() {

    override fun onBindViewHolder(holder: CardPhotoViewHolder, position: Int) {
        holder.photo.setImageURI(Uri.parse(photos[position]))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPhotoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_photo, parent, false)
        return CardPhotoViewHolder(view)
    }


    override fun getItemCount(): Int {
        return photos.size
    }


    class CardPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photo: ImageView = itemView.findViewById(R.id.photo1)
    }


}