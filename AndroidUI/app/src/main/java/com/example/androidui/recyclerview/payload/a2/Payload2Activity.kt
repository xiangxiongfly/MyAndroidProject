package com.example.androidui.recyclerview.payload.a2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.androidui.R
import com.example.androidui.recyclerview.payload.MyPhoto
import com.example.core.base.BaseActivity

class Payload2Activity : BaseActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var adapter: MyPhotoAdapter

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, Payload2Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payload)
        rv = findViewById<RecyclerView>(R.id.rv)
        adapter = MyPhotoAdapter().apply {
            setOnItemClickListener(object : MyPhotoAdapter.OnItemClickListener {
                override fun onItemCountClick(position: Int) {
                    val list = adapter.getItems()
                    val newList = list.map { it.copy() }
                    newList[position].count++
                    adapter.submitList(newList)
                }

                override fun onItemNameClick(position: Int) {
                    val list = adapter.getItems()
                    val newList = list.map { it.copy() }
                    newList[position].name += "1"
                    adapter.submitList(newList)
                }
            })
        }
        rv.adapter = adapter
        adapter.submitList(generateItems())
    }

    private fun generateItems(): List<MyPhoto> {
        val list = mutableListOf<MyPhoto>()
        val images = listOf(
            "https://so.360tres.com/dmsmty/80_80_100/t0152f90592e1be8660.webp",
            "https://so.360tres.com/dmsmty/80_80_100/t014ebd2485b9a8f681.webp",
            "https://so.360tres.com/dmsmty/80_80_100/t0140829fae40f271c3.webp",
            "https://so.360tres.com/dmsmty/80_80_100/t01ed110fbb1666b396.webp",
            "https://so.360tres.com/dmsmty/80_80_100/t016b7a88db28610b9e.webp",
            "https://so.360tres.com/dmsmty/80_80_100/t016b7a88db28610b9e.webp",
            "https://so.360tres.com/dmsmty/80_80_100/t018a1e36270f52e1bc.webp",
        )
        for (i in 0..30) {
            list.add(MyPhoto(i, images.random(), "name-$i", i))
        }
        return list
    }
}