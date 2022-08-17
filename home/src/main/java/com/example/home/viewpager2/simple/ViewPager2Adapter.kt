package com.example.home.viewpager2.simple

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R

class ViewPager2Adapter(private val context: Context, private val mImgIds: IntArray) :
    RecyclerView.Adapter<ViewPager2Adapter.MyViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflater.inflate(R.layout.item_image_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(mImgIds[position])
    }

    override fun getItemCount(): Int {
        return mImgIds.size
    }

    class MyViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        public val imageView: ImageView = itemView as ImageView
    }
}