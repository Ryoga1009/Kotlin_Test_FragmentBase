package com.ryoga.kotlintest.addcustomview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.Counter
import com.ryoga.kotlintest.R
import com.ryoga.kotlintest.list.ListAdapter

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [AddCustomViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCustomViewFragment : Fragment() {
    // TODO: Rename and change types of parameters

    var num: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add_custom_view, container, false)


        var list = view.findViewById<ListView>(R.id.list)

        var countList = arrayListOf<Counter>(
            Counter(0)
        )
        Log.d("MYE", requireContext().toString() + "--" + countList.size)
        val adapter = ListAdapter(requireContext(), countList)
        list.adapter = adapter


        view.findViewById<Button>(R.id.button_add_customview).setOnClickListener {
            countList.add(Counter(num))
            num++
            adapter.notifyDataSetChanged()
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddCustomViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCustomViewFragment().apply {
                
            }
    }
}