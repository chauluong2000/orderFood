package com.example.order_food.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.order_food.Model.ItemsModel
import com.example.order_food.databinding.ViewholderRecommendedBinding
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

import com.bumptech.glide.request.RequestOptions;
import com.example.order_food.Activity.DetailActivity

class PopularAdapter(var item: MutableList<ItemsModel>):
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private var context: Context ?= null

    class ViewHolder(var binding: ViewholderRecommendedBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
        context = parent.context
        var binding = ViewholderRecommendedBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleTxt.text = item[position].title
        holder.binding.priceTxt.text = "$" + item[position].price.toString()
        holder.binding.ratingTxt.text = item[position].rating.toString()


        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(item[position].picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity:: class.java)
            intent.putExtra("object", item[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = item.size
}