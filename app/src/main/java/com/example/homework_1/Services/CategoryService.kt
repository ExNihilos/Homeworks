package com.example.homework_1.Services

import com.example.homework_1.Domain.Models.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService
{
    @GET("categories")
    fun getCategories() : Call<MutableList<Category>>
}