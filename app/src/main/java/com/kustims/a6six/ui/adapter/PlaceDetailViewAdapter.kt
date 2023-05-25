package com.kustims.a6six.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kustims.a6six.R
import com.kustims.a6six.data.model.response.DetailPlaceResponse
import com.squareup.picasso.Picasso

class PlaceDetailViewAdapter(
    private val clickListener: (DetailPlaceResponse.Review) -> Unit
) : RecyclerView.Adapter<PlaceDetailViewAdapter.MyViewHolder>() {
    private val reviews: MutableList<DetailPlaceResponse.Review> = mutableListOf()

    fun updateReviews(newReviews: List<DetailPlaceResponse.Review>) {
        reviews.clear()
        reviews.addAll(newReviews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.review_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review, clickListener)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val username: TextView = view.findViewById(R.id.tv_nickname)
        private val content: TextView = view.findViewById(R.id.tv_review_content)
        private val likeCount: TextView = view.findViewById(R.id.tv_positive_review_count)
        private val dislikeCount: TextView = view.findViewById(R.id.tv_negative_review_count)
        private val stickerImageView1: ImageView = view.findViewById(R.id.tv_sticker1)
        private val stickerImageView2: ImageView = view.findViewById(R.id.tv_sticker2)
        private val starRating: ImageView = view.findViewById(R.id.imageView)
        private val img: ImageView = view.findViewById(R.id.tv_place_image)




        fun bind(review: DetailPlaceResponse.Review, clickListener: (DetailPlaceResponse.Review) -> Unit) {
            // UI elements setup
            var contentValue= review.content
            val chunkSize = 15
            val formattedContent = contentValue.chunked(chunkSize).joinToString("\n")

            username.text = review.username
            content.text = formattedContent
            likeCount.text = review.starRating.toInt().toString()
            dislikeCount.text = review.dislikeCount.toInt().toString()
            val star = review.starRating.toInt()

            //별점
            when (star) {
                1 -> starRating.setImageResource(R.drawable.ic_star1)
                2 -> starRating.setImageResource(R.drawable.ic_star2)
                3 -> starRating.setImageResource(R.drawable.ic_star3)
                4 -> starRating.setImageResource(R.drawable.ic_star4)
                5 -> starRating.setImageResource(R.drawable.ic_star5)
                else -> starRating.setImageResource(R.drawable.ic_star5)
            }


            if (review.img.isNotEmpty()) {
                Picasso.get()
                    .load(review.img)
                    .into(img)
                Log.d("img", review.img)
            }

            if (review.stickers.size > 0 && review.stickers[0].url.isNotEmpty()) {
                Picasso.get()
                    .load(review.stickers[0].url)
                    .into(stickerImageView1)
            }

            if (review.stickers.size > 1 && review.stickers[1].url.isNotEmpty()) {
                Picasso.get()
                    .load(review.stickers[1].url)
                    .into(stickerImageView2)
            }

            view.setOnClickListener {
                clickListener(review)
            }
        }
    }
}