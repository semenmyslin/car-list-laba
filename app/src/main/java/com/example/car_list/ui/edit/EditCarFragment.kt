package com.example.car_list.ui.edit

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.widget.addTextChangedListener
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

    private val index
        get() = arguments?.getInt(ARG_INDEX)

    private val car
        get() = (activity as LaunchActivity).getCar(index ?: 0)


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


            brandText.addTextChangedListener {
                checkValidForm()
            }

            modelText.addTextChangedListener {
                checkValidForm()
            }

            yearText.addTextChangedListener {
                checkValidForm()
            }


            descText.addTextChangedListener {
                checkValidForm()
            }

            editCarButton.setOnClickListener {
                if (index != null) {
                    val activityL = activity as LaunchActivity
                    (activity as LaunchActivity).editCar(
                        car.copy(
                            brandName = brandText.text.toString(),
                            year = yearText.text.toString().toLong(),
                            desc = descText.text.toString(),
                            modelName = modelText.text.toString()

                        ), index!!
                    )
                    activityL.supportFragmentManager.popBackStack()
                }
            }



            toolbar.setNavigationOnClickListener {
                val activityL = activity as LaunchActivity
                activityL.supportFragmentManager.popBackStack()
            }

        }

    }

    private fun checkValidForm() {
        with(binding) {
            editCarButton.isEnabled = brandText.text.isNotBlank()
                    && modelText.text.isNotBlank()
                    && yearText.text.isNotBlank()
                    && descText.text.isNotBlank()
        }

    }


    companion object {

        const val ARG_INDEX = "index"

        fun create(index: Int): EditCarFragment {
            val fragment = EditCarFragment()
            val args = Bundle()
            args.putInt(ARG_INDEX, index)
            fragment.arguments = args
            return fragment
        }
    }
}


