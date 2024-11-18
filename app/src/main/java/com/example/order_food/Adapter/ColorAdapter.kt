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
import com.example.order_food.databinding.ViewholderColorBinding

class ColorAdapter(var item: MutableList<String>):
    RecyclerView.Adapter<ColorAdapter.Viewholder>() {
        private var selectedPositon = - 1
    private var lastSelectedPosition = - 1
    private lateinit var context: Context


   inner class Viewholder(val binding: ViewholderColorBinding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.Viewholder {
         context = parent.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ColorAdapter.Viewholder, position: Int) {


        Glide.with(holder.itemView.context)
            .load(item[position])
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPositon
            selectedPositon = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPositon)
        }

        if (selectedPositon == position){

            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)

        }
        else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }

    }

    override fun getItemCount(): Int = item.size


}