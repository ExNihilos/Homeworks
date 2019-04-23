package com.example.homework_1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : AppCompatActivity(), CategoryAdapter.OnAdapterClickListener {


    private lateinit var RVcategory: RecyclerView
    val categories: CategoryProvider = CategoryProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        RVcategory = rvCategory
        RVcategory.layoutManager = LinearLayoutManager(this)
        RVcategory.adapter = CategoryAdapter(this,   categories.getCategory(), this)
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

    override fun onItemClick(position: Int, category: Category) {
        val intent1 = Intent(this, EditCardActivity::class.java)
        intent1.putExtra(EditCardActivity.categoryextra, category.title)
        setResult(Activity.RESULT_OK, intent1)
        finish()
    }

    fun backClick(view: View) {
       onBackPressed()
    }
}
