package com.example.order_food.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.order_food.Model.BrandModel
import com.example.order_food.R
import com.example.order_food.databinding.ViewholderBrandBinding

class BrandAdapter(var item: MutableList<BrandModel>):
    RecyclerView.Adapter<BrandAdapter.Viewholder>() {
        private var selectedPositon = - 1
    private var lastSelectedPosition = - 1
    private lateinit var context: Context


   inner class Viewholder(val binding: ViewholderBrandBinding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
         context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.Viewholder, position: Int) {
        var item = item[position]
        holder.binding.title.text = item.title
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPositon
            selectedPositon = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPositon)
        }
        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPositon == position){
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.white)))

            holder.binding.title.visibility = View.VISIBLE
        }
        else{
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.black)))

            holder.binding.title.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int = item.size


}