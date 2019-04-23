package com.example.homework_1

class CategoryProvider
{
    val categories: MutableList<Category> = mutableListOf(
        Category(1, "Одежда и обувь"),
        Category(2, "Супермаркеты"),
        Category(3, "Красота"),
        Category(4, "Автомобиль")
    )

    fun getCategory() : MutableList<Category>
    {
        return categories
    }
}