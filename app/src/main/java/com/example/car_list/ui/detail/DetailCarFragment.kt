package com.example.car_list.ui.detail

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.car_list.data.Car
import com.example.car_list.ui.LaunchActivity
import com.example.car_list.ui.addSystemTopAndBottomPadding
import com.example.car_list.ui.edit.EditCarFragment
import com.example.car_list_laba.R
import com.example.car_list_laba.databinding.FragmentDetailCarBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class DetailCarFragment : MvpAppCompatFragment(R.layout.fragment_detail_car), DetailCarView {

    @InjectPresenter
    lateinit var presenter: DetailCarPresenter

    private val binding: FragmentDetailCarBinding by viewBinding()

    private val index
        get() = arguments?.getInt("index")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }


    private fun initUi() {
        with(binding) {
            root.addSystemTopAndBottomPadding()
            val car = (activity as LaunchActivity).getCar(index ?: 0)
            car.let {
                brandText.text = it.brandName
                modelText.text = it.modelName
                Glide.with(requireContext()).load(it.image).into(image)
                yearText.text = it.year.toString()
                descText.text = it.desc
                typeEngineText.text = it.typeEngine
            }

            toolbar.setNavigationOnClickListener {
                val activityL = activity as LaunchActivity
                activityL.supportFragmentManager.popBackStack()
            }

            editCarButton.setOnClickListener {
                val activityL = activity as LaunchActivity
                car.let {
                    activityL.supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EditCarFragment.create(index = index ?: 0, isEditMode = true)).addToBackStack("list")
                        .commit()
                }

            }

        }

    }


    companion object {

        private const val ARG_INDEX = "index"

        fun create(index: Int): DetailCarFragment {
            val fragment = DetailCarFragment()
            val args = Bundle()
            args.putInt(ARG_INDEX, index)
            fragment.arguments = args
            return fragment
        }
    }
}
