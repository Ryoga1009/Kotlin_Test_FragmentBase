package com.ryoga.kotlintest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ryoga.kotlintest.Counter
import com.ryoga.kotlintest.R
import com.ryoga.kotlintest.databinding.FragmentBlankBinding
import kotlinx.android.synthetic.main.fragment_blank.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    var counter = Counter(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        var binding = FragmentBlankBinding.inflate(inflater, container, false)
        var binding = DataBindingUtil.inflate<FragmentBlankBinding>(
            inflater,
            R.layout.fragment_blank,
            container,
            false
        )


//        val binding: FragmentBlankBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(context),
//            R.layout.fragment_blank,
//            container,
//            false
//        )


        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_blank, container, false)


        binding.root.button2.setOnClickListener {

            counter.count++
            binding!!.counter = counter


        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            BlankFragment().apply {

            }
    }
}