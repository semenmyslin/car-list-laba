package com.example.car_list.ui.edit

import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
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

    var isImageSrcNotEmpty = true

    var imageSrc = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            Glide.with(requireContext()).load(uri).into(binding.image)
            imageSrc = uri.toString()
            isImageSrcNotEmpty = true
            checkValidForm()

        } else {
            Log.d("PhotoPicker", "No media selected")
            isImageSrcNotEmpty = false
            imageSrc = ""
            checkValidForm()
        }
    }


    private fun initUi() {
        val index = arguments?.getInt(ARG_INDEX)
        val editMode = arguments?.getBoolean(ARG_EDIT_MODE) ?: false
        val car = if (index != null && editMode) (activity as LaunchActivity).getCar(index!!) else null
        with(binding) {
            root.addSystemTopAndBottomPadding()


            car?.let {
                brandText.text = SpannableStringBuilder(it.brandName)
                modelText.text = SpannableStringBuilder(it.modelName)
                yearText.text = SpannableStringBuilder(it.year.toString())
                descText.text = SpannableStringBuilder(it.desc)
                typeEngineText.text = SpannableStringBuilder(it.typeEngine)
                imageSrc = it.image
                Glide.with(requireContext()).load(it.image).into(image)
            } ?: kotlin.run {
                isImageSrcNotEmpty = false
                Glide.with(requireContext()).load(android.R.drawable.ic_input_add).into(image)
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

            typeEngineText.addTextChangedListener {
                checkValidForm()
            }


            descText.addTextChangedListener {
                checkValidForm()
            }

            image.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            if (car == null) {
                editCarButton.text = "Добавить машину"
                addCarButton.text = "Добавление машины"
            }

            editCarButton.setOnClickListener {
                val activityL = activity as LaunchActivity

                if (car != null) {
                    activityL.editCar(
                        Car(
                            brandName = brandText.text.toString(),
                            year = yearText.text.toString().toLong(),
                            desc = descText.text.toString(),
                            modelName = modelText.text.toString(),
                            image = imageSrc,
                            typeEngine = typeEngineText.text.toString()

                        ), index!!
                    )
                } else {
                    activityL.addCar(
                        Car(
                            brandName = brandText.text.toString(),
                            year = yearText.text.toString().toLong(),
                            desc = descText.text.toString(),
                            modelName = modelText.text.toString(),
                            image = imageSrc,
                            typeEngine = typeEngineText.text.toString()

                        )
                    )
                }

                activityL.supportFragmentManager.popBackStack()
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
                    && isImageSrcNotEmpty
        }

    }


    companion object {

        const val ARG_INDEX = "index"
        const val ARG_EDIT_MODE = "edit"

        fun create(index: Int?, isEditMode: Boolean): EditCarFragment {
            val fragment = EditCarFragment()
            val args = Bundle()
            args.putBoolean(ARG_EDIT_MODE, isEditMode)
            if (index != null)
                args.putInt(ARG_INDEX, index)
            fragment.arguments = args
            return fragment
        }
    }
}


