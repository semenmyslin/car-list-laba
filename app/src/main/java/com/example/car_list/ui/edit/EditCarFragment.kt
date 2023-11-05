package com.example.car_list.ui.edit

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.car_list.data.Car
import com.example.car_list.ui.LaunchActivity
import com.example.car_list.ui.addSystemTopAndBottomPadding
import com.example.car_list.ui.detail.DetailCarFragment
import com.example.car_list_laba.R
import com.example.car_list_laba.databinding.FragmentCarEditBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class EditCarFragment : MvpAppCompatFragment(R.layout.fragment_car_edit), EditCarView {


    @InjectPresenter
    lateinit var presenter: EditCarPresenter

    private val binding: FragmentCarEditBinding by viewBinding()

    private val car
        get() = arguments?.getParcelable<Car>(ARG_CAR)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }


    private fun initUi() {
        with(binding) {
            root.addSystemTopAndBottomPadding()
            car?.let {
                brandText.text = SpannableStringBuilder(it.brandName)
                modelText.text = SpannableStringBuilder(it.modelName)
                yearText.text = SpannableStringBuilder(it.year.toString())
                descText.text = SpannableStringBuilder(it.desc)
            }

            toolbar.setNavigationOnClickListener {
                val activityL = activity as LaunchActivity
                activityL.supportFragmentManager.popBackStack()
            }

        }

    }


    companion object {

        const val ARG_CAR = "car"

        fun create(car: Car): EditCarFragment {
            val fragment = EditCarFragment()
            val args = Bundle()
            args.putParcelable(ARG_CAR, car)
            fragment.arguments = args
            return fragment
        }
    }
}


