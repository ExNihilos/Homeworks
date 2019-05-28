package com.example.homework_1.Presentation.Card

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.homework_1.Domain.Models.Card
import com.example.homework_1.Domain.Models.Category
import com.example.homework_1.Domain.Models.Image
import com.example.homework_1.Presentation.Category.CategoryListActivity
import com.example.homework_1.Providers.CardProvider
import com.example.homework_1.R
import kotlinx.android.synthetic.main.activity_edit_card_activty.*


class EditCardActivity : AppCompatActivity() {

    var cardProvider = CardProvider()
    var id = cardProvider.getId()?.plus(1)

    companion object
    {
        const val REQUEST_CODE = 1
        var categoryextra = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_card_activty)
    }

    fun backClick(view: View)
    {
        onBackPressed()
    }

    fun saveClick(view: View)
    {
        if (id==null)
        {
            id=1
        }

        val name = NameIT.text.toString()
        val category = Category(id!!.toInt(), CategoryIT.text.toString())
        val percent = PercentIT.text.toString()
        val images = mutableListOf(
            Image(1, "android.resource://com.example.homework_1/drawable/gaini_47"),
            Image(2, "android.resource://com.example.homework_1/drawable/gaini_47")
        )


        if (name == "" || category.title == "" || percent == "")
        {
            Toast.makeText(this, "Заполнены не все поля", LENGTH_SHORT).show()
        }

        else
        {
            if (id==null)
            {
                id=1
            }

            val card1 = Card(
                id!!, name, category, try {
                    percent.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Неверный формат скидки", LENGTH_SHORT).show(); return
                }, images
            )

            if (percent.toInt() > 100)
            {
                Toast.makeText(this, "Скидка не может быть больше 100%!", LENGTH_SHORT).show()
                return
            }

            cardProvider.saveCardToDB(card1)
            id = id!!+1
            val intent3 = Intent(this, CardListActivity::class.java)
            intent3.putExtra(Card::class.java.simpleName, card1)
            setResult(Activity.RESULT_OK, intent3)
            finish()
        }
    }

    fun categoryClick(view: View)
    {
        val intent1 = Intent(this, CategoryListActivity::class.java)
        startActivityForResult(intent1,
            REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
        {
            CategoryIT.setText(data?.getStringExtra(categoryextra))
        }
    }
}


