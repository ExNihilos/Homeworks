package com.example.homework_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_edit_card_activty.*


class EditCardActivity : AppCompatActivity() {

    var id: Int = 1

    companion object
    {
        const val REQUEST_CODE = 1
        var categoryextra = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_card_activty)
    }


    fun backClick(view: View) {
        onBackPressed()
    }


    fun saveClick(view: View) {

        val name = NameIT.text.toString()
        val category = Category(id,CategoryIT.text.toString())
        val percent = PercentIT.text.toString()
        val photos = arrayListOf(
            "android.resource://com.example.homework_1/drawable/gaini_47",
            "android.resource://com.example.homework_1/drawable/gaini_47"
        )


        if (name == "" || category.title == "" || percent == "") {
            Toast.makeText(this, "Заполнены не все поля", LENGTH_SHORT).show()
        }

        else {

            val card1 = Card(id,name, category, try {percent.toInt()}
            catch (e:NumberFormatException){Toast.makeText(this,"Неверный формат скидки", LENGTH_SHORT).show(); return}, photos)
            id++
            if (percent.toInt() > 100) {
                Toast.makeText(this, "Скидка не может быть больше 100%!", LENGTH_SHORT).show()
                return
            }

            val intent3 = Intent(this, CardListActivity::class.java)
            intent3.putExtra(Card::class.java.simpleName, card1)

            setResult(Activity.RESULT_OK, intent3)
            finish()
        }
    }


    fun categoryClick(view: View) {
        val intent1 = Intent(this, CategoryListActivity::class.java)
        startActivityForResult(intent1, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            CategoryIT.setText(data?.getStringExtra(categoryextra))
        }
    }
}


