package com.example.order_food.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.order_food.Model.BrandModel
import com.example.order_food.Model.ItemsModel
import com.example.order_food.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel(): ViewModel() {
    private var firebaseDatabase = FirebaseDatabase.getInstance()
    private var _banner = MutableLiveData<List<SliderModel>>()
    private var _brand = MutableLiveData<MutableList<BrandModel>>()
    private var _popular = MutableLiveData<MutableList<ItemsModel>>()

    var brands: LiveData<MutableList<BrandModel>> = _brand
    var banners: LiveData<List<SliderModel>> = _banner
    var popular: LiveData<MutableList<ItemsModel>> = _popular


    fun loadBanners(){
        var Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var lists = mutableListOf<SliderModel> ()
                for (childSnapshot in snapshot.children){
                    var list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    fun loadBrand(){
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadPupolar(){
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}