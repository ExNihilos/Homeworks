package com.example.homework_1.Presentation.Category

import android.util.Log
import com.example.homework_1.Domain.Repositories.CategoryRepository
import io.reactivex.disposables.Disposable

class CategoryListPresentor
{
    var view: ICategoryListView? = null
    var repository: CategoryRepository? = null
    var subscription: Disposable? = null

    fun attachView()
    {
        view?.showProgress(true)
        subscription = repository?.loadCategories()?.subscribe(
            {
                categories ->
                view?.showCatList(categories)
                view?.showProgress(false)
            },

            {
                error ->
                Log.e("tag", "error when loading categories", error)
                error.printStackTrace()
                view?.showProgress(false)
            }
        )
    }

    fun dettachView() {
        subscription?.dispose()
    }
}