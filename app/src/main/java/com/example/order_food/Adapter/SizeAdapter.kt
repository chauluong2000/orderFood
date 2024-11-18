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
import com.example.order_food.databinding.ViewholderSizeBinding

class SizeAdapter(var item: MutableList<String>):
    RecyclerView.Adapter<SizeAdapter.Viewholder>() {
        private var selectedPositon = - 1
    private var lastSelectedPosition = - 1
    private lateinit var context: Context


   inner class Viewholder(val binding: ViewholderSizeBinding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.Viewholder {
         context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
 

        holder.binding.sizeTxt.text = item[position]
        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPositon
            selectedPositon = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPositon)
        }

        if (selectedPositon == position){

            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.purple))

        }
        else {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))
        }

    }

    override fun getItemCount(): Int = item.size


}