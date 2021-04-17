package com.ryoga.kotlintest.api

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.R
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ApiTestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ApiTestFragment : Fragment() {

    //名古屋の天気予報　https://weather.tsukumijima.net/api/forecast/city/230010


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_api_test, container, false)
        Log.d("MYE", "aaa")


        view.findViewById<Button>(R.id.button_connect).setOnClickListener {
            val handler = Handler()
            val request = Request.Builder().url("https://reftec.work/").build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    val responseText: String? = response.body?.string()
                    handler.post {
                        Toast.makeText(requireContext(), "接続", Toast.LENGTH_SHORT).show()
                        view.findViewById<TextView>(R.id.textView_result).setText(responseText)
                        Log.i("okhttp3:", responseText)
                    }
                }
            })
        }



        return view

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ApiTestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ApiTestFragment().apply {

            }
    }
}