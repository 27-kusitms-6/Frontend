package com.kustims.a6six.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kustims.a6six.R
import com.kustims.a6six.data.model.response.HomePlaceResponse
import com.squareup.picasso.Picasso

class HomeRecyclerViewAdapter (
    private val tasks: MutableList<HomePlaceResponse.Data> = mutableListOf(),
    private val clickListener:(HomePlaceResponse.Data)->Unit,
) : RecyclerView.Adapter<MyViewHolder>() {

    fun updateTasks(newTasks: List<HomePlaceResponse.Data>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.home_cardview_item,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (tasks.isNotEmpty()) {
            val task = tasks[position]
            holder.bind(task, clickListener)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
    // UI 요소에 대한 참조를 정의합니다.
    private val name: TextView = view.findViewById(R.id.tv_recommendation_item1_name)
    private val ImageView: ImageView = view.findViewById(R.id.tv_recommendation_item1_image)
    private val rating: TextView = view.findViewById(R.id.tv_recommendation_item1_rating)
    private val StickerImageView1: ImageView = view.findViewById(R.id.recommendation_item1_sticker1)
    private val StickerImageView2: ImageView = view.findViewById(R.id.recommendation_item1_sticker2)

    fun bind(task: HomePlaceResponse.Data, clickListener: (HomePlaceResponse.Data) -> Unit) {
        name.text = task.name

        if (task.img.isNotEmpty()) {
            Picasso.get()
                .load(task.img)
                .into(ImageView)
        }

        rating.text = task.rating.toString()

        if (task.stickers[0].url.isNotEmpty()) {
            Picasso.get()
                .load(task.stickers[0].url)
                .into(StickerImageView1)
        }

        if (task.stickers[1].url.isNotEmpty()) {
            Picasso.get()
                .load(task.stickers[1].url)
                .into(StickerImageView2)
        }

        view.setOnClickListener {


            //clickListener(task)
//            todoChecked = !todoChecked
//            clickListener(task)
        }
    }
}