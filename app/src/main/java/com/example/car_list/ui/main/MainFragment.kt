package com.example.car_list.ui.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.car_list.data.Car
import com.example.car_list.ui.LaunchActivity
import com.example.car_list.ui.addSystemTopAndBottomPadding
import com.example.car_list.ui.detail.DetailCarFragment
import com.example.car_list_laba.R
import com.example.car_list_laba.databinding.FragmentMainListBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class MainFragment : MvpAppCompatFragment(R.layout.fragment_main_list), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val binding: FragmentMainListBinding by viewBinding()

    private val carAdapter by lazy { CarAdapter(::openCarFragment) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        with(binding) {
            root.addSystemTopAndBottomPadding()
            binding.addCarButton.setOnClickListener {
                presenter.addCar()
            }
        }
    }

    override fun addCar(car: Car) {
        carAdapter.addCar(car)
    }

    fun openCarFragment(car: Car) {
        val activityL = activity as LaunchActivity
        activityL.supportFragmentManager.beginTransaction().hide(this).add(R.id.container,DetailCarFragment.create(car)).addToBackStack("list")
            .commit()
    }

    override fun showListCars(cars: List<Car>) {
        with(binding) {
            recycler.run {
                adapter = this@MainFragment.carAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                carAdapter.addALL(cars)
            }
        }
    }


}
