package com.example.homework_1.Services

import com.example.homework_1.Category
import retrofit2.http.GET


interface RXCategoryService
{
     @GET("categories")
     fun getCategories(): io.reactivex.Observable<List<Category>>
}