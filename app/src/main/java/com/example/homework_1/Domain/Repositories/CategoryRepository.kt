package com.example.homework_1.Domain.Repositories

import com.example.homework_1.Domain.Models.Category
import com.example.homework_1.Extensions.map2RealmList
import com.example.homework_1.Services.RXCategoryService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryRepository
{
    val retrofitRX: Retrofit = Retrofit.Builder() // Загрузка категорий из бэкэнда с помощью RX
        .baseUrl("http://cardbag.ru/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val RXservice = retrofitRX.create(RXCategoryService::class.java)

    fun loadCategories() : Observable<MutableList<Category>>
    {
        return RXservice.getCategories()
            .doOnNext {categoryList ->
                Realm.getDefaultInstance().use { realm->
                    realm.beginTransaction()
                    realm.copyFromRealm(realm.copyToRealmOrUpdate(categoryList.map2RealmList()))
                    realm.commitTransaction()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}