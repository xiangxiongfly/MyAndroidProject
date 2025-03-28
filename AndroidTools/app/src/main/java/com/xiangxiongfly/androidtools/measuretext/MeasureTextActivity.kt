package com.xiangxiongfly.androidtools.measuretext

import android.graphics.Rect
import android.os.Bundle
import android.text.Layout
import android.text.StaticLayout
import android.util.Log
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.exts.dp


class MeasureTextActivity : BaseActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_text)
        textView1 = findViewById<TextView>(R.id.textView1)
        textView2 = findViewById<TextView>(R.id.textView2)
        test1()
        test2()
        test3()
        test4()
        test5()
        test6()
    }

    private fun test1() {
        textView1.post {
            val width = textView1.measuredWidth
            val height = textView1.measuredHeight
            Log.e("TAG", "方法一：width:${width} height:${height}")
        }
    }

    private fun test2() {
        val paint = textView1.paint
        val text = textView1.text.toString()
        val width = paint.measureText(text)
        Log.e("TAG", "方式二：width:${width}")
    }

    private fun test3() {
        val fontMetrics = textView1.paint.fontMetrics
        val height = fontMetrics.bottom - fontMetrics.top
        Log.e("TAG", "方法三：height:${height}")
    }

    private fun test4() {
        val bounds = Rect()
        val paint = textView1.paint
        val text = textView1.text.toString()
        paint.getTextBounds(text, 0, text.length, bounds)
        val width = bounds.width()
        val height = bounds.height()
        Log.e("TAG", "方法四：width:$width height:$height")
    }

    private fun test5() {
        val paint = textView1.paint
        val text = textView1.text.toString()
        val arr = FloatArray(text.length)
        paint.getTextWidths(text, arr)
        var width = 0F
        arr.forEach {
            width += it
        }
        Log.e("TAG", "方法五：width:$width")
    }

    private fun test6() {
        val paint = textView1.paint
        val text = textView1.text.toString()
        val staticLayout = StaticLayout.Builder.obtain(text, 0, text.length, paint, 30.dp)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(0f, 1.0f)
            .setIncludePad(false)
            .build();
        val width = staticLayout.width
        val height = staticLayout.height
        Log.e("TAG", "方法六：width:$width height:$height")
    }
}