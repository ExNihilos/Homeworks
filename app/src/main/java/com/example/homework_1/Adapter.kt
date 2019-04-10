package com.example.homework_1
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Adapter(var context: Context, var data: List<Category>, private var listener: OnAdapterClickListener?) :
    RecyclerView.Adapter<Adapter.CategoryViewHolder>() {
    override fun onBindViewHolder(p0: CategoryViewHolder, p1: Int) {
        p0?.itemView?.tag = data[p1]
        p0?.title?.text = data[p1].title
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.item_view_category, p0, false)
        return CategoryViewHolder(view, listener)
    }

    /*override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_view_category,parent,false)
        return  CategoryViewHolder(view, listener)
    }*/

    /*override  fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
        holder?.itemView?.tag = data[position]
        holder?.title?.text = data[position].title
    }*/

    override fun getItemCount(): Int {
        return data.size
    }

    class CategoryViewHolder(itemView: View?, private val listener: OnAdapterClickListener?) : RecyclerView.ViewHolder(
        itemView!!
    ), View.OnClickListener {
        var title: TextView = itemView!!.findViewById(R.id.tv_CategoryItem)

        init {
            itemView!!.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            listener?.onItemClick(adapterPosition, itemView.tag as Category)
        }
    }


    interface OnAdapterClickListener {
        fun onItemClick(position: Int, category: Category)
    }
}