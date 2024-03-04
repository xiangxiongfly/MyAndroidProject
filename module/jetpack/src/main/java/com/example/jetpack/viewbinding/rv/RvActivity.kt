package com.example.jetpack.viewbinding.rv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.databinding.ActivityRvBinding
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.databinding.ItemTextBinding

class RvActivity : BaseActivity() {
    private var _viewBinding: ActivityRvBinding? = null
    private val viewBinding get() = _viewBinding!!

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RvActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityRvBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initRV()
    }

    private fun initRV() {
        val data = ArrayList<String>().apply {
            for (i in 1..30) {
                add("hello:$i")
            }
        }
        val adapter = MyAdapter(mContext, data)
        viewBinding.recyclerView.adapter = adapter
    }

    class MyAdapter(context: Context, private val data: ArrayList<String>) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text: TextView = itemView.findViewById(R.id.text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val viewBinding = ItemTextBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(viewBinding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = data[position]
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}

