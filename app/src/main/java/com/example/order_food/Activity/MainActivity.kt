package com.example.order_food.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.order_food.Adapter.BrandAdapter
import com.example.order_food.Adapter.PopularAdapter
import com.example.order_food.Model.SliderModel

import com.example.order_food.databinding.ActivityMainBinding
import com.example.order_food.Adapter.SliderAdapter
import com.example.order_food.viewModel.MainViewModel

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initBanner()
        initBrand()
        initPopular()
        initBottomMenu()

        }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener{
            startActivity(
                Intent(
                    this@MainActivity,
                    CartActivity::class.java

                )
            )
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer {item ->
            banners(item)
            binding.progressBarBanner.visibility = View.GONE

        })
        viewModel.loadBanners()
    }

    private fun banners(images: List<SliderModel>){
        binding.viewpageSlider.adapter = SliderAdapter(images, binding.viewpageSlider)
        binding.viewpageSlider.clipToPadding = false
        binding.viewpageSlider.clipChildren = false
        binding.viewpageSlider.offscreenPageLimit = 3
        binding.viewpageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewpageSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1){
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewpageSlider)
        }
    }

    private fun initBrand(){
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewBrand.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewBrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrand()
    }

    private fun initPopular(){
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewPopular.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.viewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPupolar()
    }
}
