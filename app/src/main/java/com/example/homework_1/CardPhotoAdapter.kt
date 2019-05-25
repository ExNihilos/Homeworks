package com.example.homework_1

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.homework_1.Domain.Models.Image

class CardPhotoAdapter(var images: MutableList<Image>) : RecyclerView.Adapter<CardPhotoAdapter.CardPhotoViewHolder>() {

    override fun onBindViewHolder(holder: CardPhotoViewHolder, position: Int)
    {
        holder.image.setImageURI(Uri.parse(images[position].url))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPhotoViewHolder
    {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_photo, parent, false)
        return CardPhotoViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return images.size
    }


    class CardPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.photo1)
    }


}