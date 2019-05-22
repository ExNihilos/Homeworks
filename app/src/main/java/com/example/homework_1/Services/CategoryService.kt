package com.example.homework_1.Services

import com.example.homework_1.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService
{
    @GET("categories")
    fun getCategories() : Call<List<Category>>
}