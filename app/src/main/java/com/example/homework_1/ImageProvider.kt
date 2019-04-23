package com.example.homework_1

class ImageProvider
{
    val images: MutableList<Image> = mutableListOf(
        Image(0,"android.resource://com.example.homework_1/drawable/gaini_47"),
        Image(1,"android.resource://com.example.homework_1/drawable/gaini_47")
    )

    fun getImageById(cardId: Int) : MutableList<Image>
    {
        return images
    }

}