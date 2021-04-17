package com.ryoga.kotlintest

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.addbuttontest.ButtonAddingFragment
import com.ryoga.kotlintest.addcustomview.AddCustomViewFragment
import com.ryoga.kotlintest.api.ApiTestFragment
import com.ryoga.kotlintest.fragment.BlankFragment
import com.ryoga.kotlintest.list.ListFragment
import com.ryoga.kotlintest.loop.LoopFragment
import com.ryoga.kotlintest.sensor.AccelerationSensorFragment
import com.ryoga.kotlintest.sensor.BarometricPressureSensorFragment
import com.ryoga.kotlintest.sensor.BleFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (savedInstanceState == null) {
            findViewById<Button>(R.id.button_binding).setOnClickListener {
                //Fragment切り替え
//                replaceFragment(BlankFragment.newInstance())
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(
                    R.id.conteinar,
                    BlankFragment.newInstance()
                )
                transaction.commit()
//
            }
            findViewById<Button>(R.id.button_customlist).setOnClickListener {
                //Fragment切り替え
                replaceFragment(ListFragment.newInstance())
//
            }
            findViewById<Button>(R.id.button_addview).setOnClickListener {
                //Fragment切り替え
                replaceFragment(ButtonAddingFragment())
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(
                    R.id.conteinar,
                    ButtonAddingFragment.newInstance()
                )
                transaction.commit()
//
            }

            findViewById<Button>(R.id.button_add_custom).setOnClickListener {
                replaceFragment(AddCustomViewFragment())
            }
            findViewById<Button>(R.id.button_api).setOnClickListener {
                replaceFragment(ApiTestFragment())
            }
            findViewById<Button>(R.id.button_loop).setOnClickListener {
                replaceFragment(LoopFragment())
            }
            findViewById<Button>(R.id.button_acc).setOnClickListener {
                replaceFragment(AccelerationSensorFragment())
            }
            findViewById<Button>(R.id.button_bar).setOnClickListener {
                replaceFragment(BarometricPressureSensorFragment())
            }
            findViewById<Button>(R.id.button_bluetooth).setOnClickListener {
                replaceFragment(BleFragment())
            }


        }
    }


    //Fragment切り替え
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.conteinar, fragment)
        fragmentTransaction.commit()
    }

    fun replaceFragment2(fragment: Fragment) {

    }
}
//                AddMenuFragment.newInstance()
