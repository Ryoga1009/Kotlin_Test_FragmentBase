package com.ryoga.kotlintest.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ryoga.kotlintest.Counter
import com.ryoga.kotlintest.R

class ListAdapter(val context: Context, val CounterList: ArrayList<Counter>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragment_content, null)
        val num = view.findViewById<TextView>(R.id.content)

        val counter = CounterList[position]
        num.setText(counter.count.toString())

        view.findViewById<Button>(R.id.button_content).setOnClickListener {
            Toast.makeText(context, "${position}番目のボタン\n好きなレイアウト/挙動がListにできるやつ", Toast.LENGTH_SHORT)
                .show()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return CounterList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return CounterList.size
    }
}