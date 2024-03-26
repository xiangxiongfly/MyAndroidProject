package com.example.jetpack.viewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import com.example.base.BaseFragment
import com.example.jetpack.R

class MenuFragment : BaseFragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    companion object {
        fun newInstance() = MenuFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.listView)
        val data = ArrayList<String>().apply {
            for (i in 0..20) {
                add("menu$i")
            }
        }
        listView.adapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, data)
        listView.setOnItemClickListener { parent, view, position, id ->
            sharedViewModel.select(data[position])
        }
    }
}