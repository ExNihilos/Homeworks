package com.example.homework_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.homework_1.CardListActivity.Companion.REQUEST_CODE
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.activity_edit_card_activty.*


class EditCardActivty : AppCompatActivity() {


    companion object {
        const val REQUEST_CODE = 1
        var categoryextra = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_card_activty)
    }


    fun backClick(view: View) {
        val intent2 = Intent(this, CardListActivity::class.java)

        setResult(Activity.RESULT_CANCELED, intent2)
        finish()
    }


    fun saveClick(view: View) {
        var name = NameIT.text.toString()
        var category = CategoryIT.text.toString()
        var percent = PercentIT.text.toString().toInt()
        var card1 = Card(name, category, percent)

        if (percent > 100) {
            Toast.makeText(this, "Скидка не может быть больше 100%!", LENGTH_SHORT).show()
            return
        }

        val intent3 = Intent(this, CardListActivity::class.java)
        intent3.putExtra(Card::class.java.simpleName, card1)

        setResult(Activity.RESULT_OK, intent3)
        finish()
    }

    fun categoryClick(view: View) {
        val intent1 = Intent(this, CategoryListActivity::class.java)
        startActivityForResult(intent1, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            var a = intent.getStringExtra(categoryextra)

            CategoryIT.setText(data?.getStringExtra(categoryextra))
        }
    }
}


