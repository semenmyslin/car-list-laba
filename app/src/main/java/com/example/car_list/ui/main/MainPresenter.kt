package com.example.car_list.ui.main

import android.content.Context
import com.example.car_list.data.Car
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {


    override fun attachView(view: MainView?) {
        super.attachView(view)
        viewState.showListCars()

    }

    fun addCar() {
        viewState.addCar(Car.getDefaultCar())
    }


}
