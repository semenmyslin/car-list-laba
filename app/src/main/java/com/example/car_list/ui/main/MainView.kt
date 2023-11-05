package com.example.car_list.ui.main

import com.example.car_list.data.Car
import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface MainView : MvpView {

    @OneExecution
    fun showListCars(cars: List<Car>)

    @OneExecution
    fun addCar(car : Car)
}
