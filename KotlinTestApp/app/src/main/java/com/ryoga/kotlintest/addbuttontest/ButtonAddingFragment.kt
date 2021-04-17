package com.ryoga.kotlintest.addbuttontest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ButtonAddingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ButtonAddingFragment : Fragment() {
    // TODO: Rename and change types of parameters

    //EditTextのリスト
    var editTextList = arrayListOf<EditText>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_button_adding, container, false)


        val linerlayout = view.findViewById<LinearLayout>(R.id.linerlayout)


        //EditTextの追加
        view.findViewById<Button>(R.id.button_adding).setOnClickListener {
            var editText = EditText(requireContext())
            editText.setTextSize(30f)
            editText.setText(editTextList.size.toString())

            editTextList.add(editText)
//            editText.id = "@+id/edit1"
            linerlayout.addView(editText)
        }

        view.findViewById<Button>(R.id.button_getall).setOnClickListener {
            val view_parent = view.findViewById<LinearLayout>(R.id.parent)

//            Toast.makeText(requireContext(), view_parent.childCount.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), editTextList.size.toString(), Toast.LENGTH_SHORT)
                .show()

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
         * @return A new instance of fragment ButtonAddingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ButtonAddingFragment().apply {

            }
    }
}