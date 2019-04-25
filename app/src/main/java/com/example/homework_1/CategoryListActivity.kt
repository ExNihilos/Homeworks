package com.example.homework_1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : AppCompatActivity(), CategoryAdapter.OnAdapterClickListener {


    private lateinit var RVcategory: RecyclerView
    val categories: CategoryProvider = CategoryProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        Realm.init(this)
        RVcategory = rvCategory
        RVcategory.layoutManager = LinearLayoutManager(this)
        RVcategory.adapter = CategoryAdapter(this,   categories.getCategory(), this)
        RVcategory.addItemDecoration(DividerItemDecoration(this,1))
        var categories_list = categories.getCategory()
        Realm.getDefaultInstance().use { realm->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(categories_list.map2RealmList()))
            realm.commitTransaction()
        }
       /* realm.beginTransaction()
        var categories = categories.getCategory()
        var categoriess = realm.createObject(CategoryRealm::class.java,1)
        categoriess.categorylist = categories.getCategory()
        realm.commitTransaction()*/

    }

    /*fun getCategories(): List<Category> {
        val categories: MutableList<Category> = ArrayList()
        for (i in 1..100) {
            categories.add(Category(1,"Категория $i"))
        }
        categories.add(Category(2,"Одежда и обувь"))
        categories.add(Category(3,"Супермаркеты"))
        categories.add(Category(4,"Автомобили"))
        categories.add(Category(5,"Красота"))
        return categories
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
