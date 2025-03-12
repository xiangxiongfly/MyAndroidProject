package com.example.home.spinner

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.base.utils.showToast
import com.example.home.R

class SpinnerActivity : BaseActivity() {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private val items = arrayOf("选项1", "选项2", "选项3", "选项4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        spinner3 = findViewById(R.id.spinner3)

        iniSpinner1()
        iniSpinner2()
        iniSpinner3()
    }

    private fun iniSpinner1() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner1.onItemSelectedListener =
            object : android.widget.AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: android.widget.AdapterView<*>?,
                    view: android.view.View?,
                    position: Int,
                    id: Long
                ) {
                    showToast(items[position])
                }

                override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                    showToast("未选择")
                }
            }
    }

    private fun iniSpinner2() {
        val adapter = ArrayAdapter(this, R.layout.spinner_layout, items)
        spinner2.adapter = adapter
        spinner2.setPopupBackgroundResource(R.drawable.pop)
        spinner2.post {
            spinner2.dropDownVerticalOffset = spinner2.measuredHeight
        }
    }

    private fun iniSpinner3() {
        spinner3.adapter = MySpinnerAdapter(this, items)
        spinner3.setPopupBackgroundResource(R.drawable.pop)
        spinner3.post {
            spinner3.dropDownVerticalOffset = spinner3.measuredHeight
        }
    }

    class MySpinnerAdapter(private val context: Context, private val list: Array<String>) :
        BaseAdapter() {
        override fun getCount() = list.size

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val itemView = LayoutInflater.from(context).inflate(R.layout.spinner_layout2, null)
            val textView = itemView.findViewById<TextView>(R.id.textView)
            textView.text = list[position]
            return itemView
        }
    }
}