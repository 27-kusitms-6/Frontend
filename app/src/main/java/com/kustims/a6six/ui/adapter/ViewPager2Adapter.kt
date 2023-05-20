package com.kustims.a6six.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kustims.a6six.R
import com.kustims.a6six.ui.fragment.home.HomeFragment

class ViewPager2Adapter(
    private val context: HomeFragment,
    private val imageList: MutableList<Int>
) : RecyclerView.Adapter<ViewPager2Adapter.PagerViewHolder>() {

    // View를 담을 ViewHolder 클래스를 정의
    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: ImageView = itemView.findViewById(R.id.imageSlider)
    }

    // ViewHolder를 인스턴스화하고 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_slider,
            parent,
            false
        )
        return PagerViewHolder(view)
    }

    // 뷰와 데이터를 바인딩
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.item.setImageDrawable(
            ContextCompat.getDrawable(
                holder.item.context,
                imageList[position]
            )
        )
    }

    override fun getItemCount(): Int = imageList.size

}
