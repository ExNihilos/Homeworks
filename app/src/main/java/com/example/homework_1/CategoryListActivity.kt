package com.example.homework_1

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.homework_1.Extensions.map2RealmList
import com.example.homework_1.Providers.CategoryProvider
import com.example.homework_1.Services.CategoryService
import com.example.homework_1.Services.RXCategoryService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_category_list.*
import retrofit2.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CategoryListActivity : AppCompatActivity(), CategoryAdapter.OnAdapterClickListener {


    private lateinit var RVcategory: RecyclerView
    //val categories: CategoryProvider = CategoryProvider() // Локальный провайдер категорий


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        //progressBar.visibility = View.INVISIBLE
        Realm.init(this)
        RVcategory = rvCategory
        RVcategory.layoutManager = LinearLayoutManager(this)
        RVcategory.addItemDecoration(DividerItemDecoration(this,1))
        //RVcategory.adapter = CategoryAdapter(this,   categories.getCategory(), this) // Локальная загрузка категорий


        val retrofitRX: Retrofit = Retrofit.Builder() // Загрузка категорий из бэкэнда с помощью RX
            .baseUrl("http://cardbag.ru/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val RXservice = retrofitRX.create(RXCategoryService::class.java)
        val reposRx: Observable<List<Category>> = RXservice.getCategories()
        reposRx.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categories ->
                    var categoryList = categories as MutableList<Category>
                    RVcategory.adapter = CategoryAdapter(this,categoryList,this)
                    progressBar.visibility = View.GONE
                    Realm.getDefaultInstance().use { realm->
                        realm.beginTransaction()
                        realm.copyFromRealm(realm.copyToRealmOrUpdate(categoryList.map2RealmList()))
                        realm.commitTransaction()
                    }
                },

                { error ->
                    Log.e("TAG","Error when loading categories",error)
                    error.printStackTrace()
                    progressBar.visibility = View.GONE
                }
            )


         //var categories_list = categories.getCategory() Локальный список категорий


       /* val retrofit: Retrofit = Retrofit.Builder()            // Загрузка категорий из бэкэнда с помощью Retrofit
            .baseUrl("http://cardbag.ru/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CategoryService::class.java)
        val repos: Call<List<Category>> = service.getCategories()
        repos.enqueue(object : Callback<List<Category>>{
            override fun onFailure(call: Call<List<Category>>, t: Throwable)
            {
                Log.e("error","Error when loading categories", t)
                t.printStackTrace()
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>)
            {
                Log.d("information","categories count = ${response.body()!!.size}")
                val categoryList = response.body() as MutableList<Category>
                progressBar.visibility = View.GONE
                RVcategory.adapter = CategoryAdapter(this@CategoryListActivity, categoryList, this@CategoryListActivity)
                Realm.getDefaultInstance().use{realm ->
                    realm.beginTransaction()
                    realm.copyFromRealm(realm.copyToRealmOrUpdate((categoryList.map2RealmList())))
                    realm.commitTransaction()
                }
            }
        })*/
    }

    /*fun getCategories(): List<Category> {
        val categories: MutableList<Category> = ArrayList()
        for (i in 1..100) {
            categories.add(Category(1,"Категория $i"))
        }
    }*/

    override fun onItemClick(position: Int, category: Category)
    {
        val intent1 = Intent(this, EditCardActivity::class.java)
        intent1.putExtra(EditCardActivity.categoryextra, category.title)
        setResult(Activity.RESULT_OK, intent1)
        finish()
    }

    fun backClick(view: View)
    {
       onBackPressed()
    }
}
