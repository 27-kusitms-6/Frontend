package com.kustims.a6six.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kustims.a6six.R
import com.kustims.a6six.data.model.response.RecommendationResponse
import com.squareup.picasso.Picasso

class RecommendationViewAdapter(
    private val clickListener: (RecommendationResponse.Place) -> Unit
) : RecyclerView.Adapter<RecommendationViewAdapter.MyViewHolder>() {
    private val places: MutableList<RecommendationResponse.Place> = mutableListOf()

    fun updatePlaces(newPlaces: List<RecommendationResponse.Place>) {
        places.clear()
        places.addAll(newPlaces)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.recommendation_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place, clickListener)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.tv_restaurant_name)
        private val content: TextView = view.findViewById(R.id.tv_restaurant_intro)
        private val starRating: TextView = view.findViewById(R.id.tv_score)
        private val openingHours: TextView = view.findViewById(R.id.tv_business_hours)
        private val reviewCount: TextView = view.findViewById(R.id.tv_review_count)
        private val placeImg: ImageView = view.findViewById(R.id.tv_restaurant_image)
        private val stickerImageView1: ImageView = view.findViewById(R.id.tv_sticker1)
        private val stickerImageView2: ImageView = view.findViewById(R.id.tv_sticker2)

        fun bind(place: RecommendationResponse.Place, clickListener: (RecommendationResponse.Place) -> Unit) {
            // UI elements setup
            name.text = place.name
            content.text = place.content
            starRating.text = place.starRating.toString()
            openingHours.text = place.openingHours
            reviewCount.text = place.reviewCount.toString()

            if (place.placeImg.isNotEmpty()) {
                Picasso.get()
                    .load(place.placeImg)
                    .into(placeImg)
            }

            if (place.top2stickers.size > 0 && place.top2stickers[0].url.isNotEmpty()) {
                Picasso.get()
                    .load(place.top2stickers[0].url)
                    .into(stickerImageView1)
            }

            if (place.top2stickers.size > 1 && place.top2stickers[1].url.isNotEmpty()) {
                Picasso.get()
                    .load(place.top2stickers[1].url)
                    .into(stickerImageView2)
            }

            view.setOnClickListener {
                clickListener(place)
            }
        }
    }
}