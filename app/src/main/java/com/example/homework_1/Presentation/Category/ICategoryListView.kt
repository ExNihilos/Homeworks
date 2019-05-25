package com.example.homework_1.Presentation.Category

import com.example.homework_1.Domain.Models.Category

interface ICategoryListView
{
    fun showMessage(s : String)
    fun showCatList(categoryList: MutableList<Category>)
    fun showProgress(b: Boolean)
}