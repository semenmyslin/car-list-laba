package com.example.car_list.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.car_list.ui.main.MainFragment
import com.example.car_list_laba.R
import com.example.car_list_laba.databinding.LayoutContainerBinding
import moxy.MvpAppCompatFragment

class LaunchActivity : AppCompatActivity() {


    private val mCurrentFragment: MvpAppCompatFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? MvpAppCompatFragment

    private val binding: LayoutContainerBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
        }
    }


}
