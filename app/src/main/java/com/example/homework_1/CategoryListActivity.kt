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

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        RVcategory = rvCategory
        RVcategory.layoutManager = LinearLayoutManager(this)
        RVcategory.adapter = CategoryAdapter(this, getCategories(),this)
    }

    fun getCategories(): List<Category>
    {
        var categories: MutableList<Category> = ArrayList()
        for (i in 1..100)
        {
            categories.add(Category("Категория $i"))

        }

        categories.add(Category("Супермаркеты"))
        categories.add(Category("Автомобили"))
        categories.add(Category("Красота"))
        return categories
    }

    override fun onItemClick(position: Int, category: Category)
    {
        val intent1 = Intent(this, EditCardActivty::class.java)
        intent1.putExtra(EditCardActivty.categoryextra, category.title)
        setResult(Activity.RESULT_OK, intent1)
        finish()

    }

    fun backClick(view: View)
    {
        val intent2 = Intent(this, EditCardActivty::class.java)
        setResult(Activity.RESULT_CANCELED, intent2)
        finish()
    }
}
