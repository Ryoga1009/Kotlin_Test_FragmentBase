package com.ryoga.kotlintest.loop

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.R

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [LoopFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 *
 *
 *
 *
 * 一定周期で処理する
 *
 */


class LoopFragment : Fragment() {
    val handler = Handler()
    var count = 0
    var r: Runnable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loop, container, false)

        var button_start = view.findViewById<Button>(R.id.button_loop_start)
        var button_stop = view.findViewById<Button>(R.id.button_loop_stop)
        button_start.setOnClickListener {

            button_start.isEnabled = false
            button_stop.isEnabled = true

            r = object : Runnable {

                override fun run() {
                    // UIスレッド
                    count++

                    view.findViewById<TextView>(R.id.textView_loop).setText(count.toString())
                    handler.postDelayed(this, 1000)


                }
            }

            handler.post(r)

        }



        button_stop.setOnClickListener {
            button_start.isEnabled = true
            button_stop.isEnabled = false
            handler.removeCallbacks(r)
            count = 0
            view.findViewById<TextView>(R.id.textView_loop).setText(count.toString())

        }

        return view

    }


    override fun onPause() {
        super.onPause()
        Log.d("MYE", "pause")
        handler.removeCallbacks(r)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoopFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoopFragment().apply {

            }
    }
}