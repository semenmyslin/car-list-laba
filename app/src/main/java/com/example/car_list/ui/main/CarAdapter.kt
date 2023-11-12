package com.example.car_list.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.car_list.data.Car
import com.example.car_list_laba.databinding.ItemCarBinding

class CarAdapter(
    private val onClick: (Int) -> Unit,
    private val onRemove: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val cars: MutableList<Car> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarHolder(
            ItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CarHolder).bind(position)
    }

    fun addCar(car: Car) {
        cars.add(car)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    fun addALL(photoItems: List<Car>) {
        this.cars.clear()
        this.cars.addAll(photoItems)
        notifyDataSetChanged()
    }

    inner class CarHolder(val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(position: Int) {
            with(binding) {
                mainInfo.text = "${cars[position].brandName}, ${cars[position].modelName}"
                additionalInfo.text = "${cars[position].year}, ${cars[position].typeEngine}"
                Glide.with(binding.root.context).load(cars[position].image).into(binding.photo)

                deleteIcon.setOnClickListener {
                    cars.remove(cars[position])
                    onRemove.invoke(position)
                    notifyDataSetChanged()
                }

                root.setOnClickListener {
                    onClick.invoke(position)
                }
            }
        }
    }
}
