package com.example.homework_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : AppCompatActivity(), Adapter.OnAdapterClickListener {


    private lateinit var RVcategory: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        RVcategory = rvCategory
        RVcategory.layoutManager = LinearLayoutManager(this)
        RVcategory.adapter = Adapter(this,aaa(),this)
    }

    fun aaa(): List<Category>
    {
        var categories: MutableList<Category> = ArrayList()
        categories.add(Category("Супермаркеты"))
        categories.add(Category("Автомобили"))
        categories.add(Category("Красота"))
        return  categories
    }

    override fun onItemClick(position: Int, category: Category) {

        Toast.makeText(this, category.title, LENGTH_SHORT).show()
    }
}
