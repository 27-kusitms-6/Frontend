package com.kustims.a6six.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kustims.a6six.R
import com.squareup.picasso.Picasso

class GridBookAdapter(private val context: Context, private val saveList: MutableList<HashMap<String,Any>>): BaseAdapter() {

    inner class GridViewHolder() {
        lateinit var imageView: ImageView
        lateinit var Name: TextView

    }

    override fun getCount() = saveList.size

    override fun getItem(position: Int) = saveList[position]

    override fun getItemId(position : Int) = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var gridViewHolder = GridViewHolder()
        if (convertView == null) {
            view = LayoutInflater
                .from(context)
                .inflate(R.layout.likelist_save_item2, parent,false)
            gridViewHolder.imageView = view.findViewById(R.id.iv_save_item)
            gridViewHolder.Name = view.findViewById(R.id.title_save_item)
            view.tag = gridViewHolder
        } else {
            gridViewHolder = view!!.tag as GridViewHolder
        }
        if (position < saveList.size) {
            Picasso.get()
                .load(saveList[position]["image"].toString())
                .into(gridViewHolder.imageView)
            gridViewHolder.Name.text = saveList[position]["name"].toString()

        }
        return view!!

    }
}