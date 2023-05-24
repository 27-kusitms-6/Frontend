package com.kustims.a6six.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kustims.a6six.R
import kotlinx.android.synthetic.main.filter_item.view.*

class GridFilterAdapter(private val context: Context, private val filterList: MutableList<HashMap<String,Any>>): BaseAdapter() {

    private var onItemClickListener: OnItemClickListener? = null
    private var selectedItems: MutableList<String> = mutableListOf() //선택한 아아ㅣ템 개수

    inner class GridViewHolder() {
        lateinit var imageView: ImageView
        lateinit var filterName: TextView
        var isClicked : Boolean = false

    }


    interface  OnItemClickListener {
        fun onItemClick(selectedItems: List<String>)
    }

    fun setOnITemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }


    override fun getCount() = filterList.size

    override fun getItem(position: Int) = filterList[position]

    override fun getItemId(position : Int) = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var gridViewHolder = GridViewHolder()

        if (convertView == null) {
            view = LayoutInflater
                .from(context)
                .inflate(R.layout.filter_item, parent,false)
            gridViewHolder.imageView = view.findViewById(R.id.iv_filter_mypage)
            gridViewHolder.filterName = view.findViewById(R.id.tv_filter_name)
            view.tag = gridViewHolder
        } else {
            gridViewHolder = view!!.tag as GridViewHolder
        }
        if (position < filterList.size) {
            gridViewHolder.imageView.setImageDrawable(ContextCompat.getDrawable(context, filterList[position]["image"].toString().toInt()))
            gridViewHolder.filterName.text = filterList[position]["name"].toString()
            gridViewHolder.isClicked = filterList[position]["isClicked"].toString().toBoolean()

            if (view != null) {
                val selectedItem = filterList[position]["name"].toString()
                //이전에 선택했던 필터 전달
                if (gridViewHolder.isClicked.equals(true))
                {
                    selectedItems.add(selectedItem)
                }

                view.setOnClickListener {
                    if (selectedItems.contains(selectedItem)) {
                        selectedItems.remove(selectedItem)
                        gridViewHolder.isClicked = false
                        // 선택 해제된 경우 아이템 스타일 변경
                        // 예시로 선택 해제 시 투명도 조정
                        view.ll_filter_mypage.setBackgroundResource(R.drawable.unselected_gridview)
                        view.tv_filter_name.setTextColor(ContextCompat.getColor(context, R.color.likeit_font_black_30))
                        Log.d("Item_Clicked_off", position.toString())
                    } else {
                        if (selectedItems.size >= 2) return@setOnClickListener // 2개 이상 선택 불가능하도록 처리
                        selectedItems.add(selectedItem)
                        gridViewHolder.isClicked = true
                        // 선택된 경우 아이템 스타일 변경
                        // 예시로 선택 시 투명도 조정
                        view.ll_filter_mypage.setBackgroundResource(R.drawable.selected_gridview)
                        view.tv_filter_name.setTextColor(ContextCompat.getColor(context, R.color.likeit_main_purple))
                        Log.d("Item_Clicked_On", position.toString())
                    }
                    Log.d("selectedItems", selectedItems.toString())
                    onItemClickListener?.onItemClick(selectedItems) //선택된 아이템 리스트 전달
                }

                // 초기 아이템 스타일 설정
                if (selectedItems.contains(filterList[position]["name"].toString())) {
                    // 선택된 아이템인 경우 스타일 변경
                    // 예시로 선택 시 투명도 조정
                    view.ll_filter_mypage.setBackgroundResource(R.drawable.selected_gridview)
                    view.tv_filter_name.setTextColor(ContextCompat.getColor(context, R.color.likeit_main_purple))
                } else {
                    view.ll_filter_mypage.setBackgroundResource(R.drawable.unselected_gridview)
                    view.tv_filter_name.setTextColor(ContextCompat.getColor(context, R.color.likeit_font_black_30))
                }
            }
        }

        return view!!

    }

}