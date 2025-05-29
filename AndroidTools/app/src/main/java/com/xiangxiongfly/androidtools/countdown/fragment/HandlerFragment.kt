package com.xiangxiongfly.androidtools.countdown.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.androidtools.countdown.utils.MyHandler
import com.xiangxiongfly.common.base.BaseFragment

class HandlerFragment : BaseFragment() {
    private lateinit var tvCountDown: TextView
    private lateinit var btnStart: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button

    companion object {
        @JvmStatic
        fun newInstance() = HandlerFragment()
    }

    private lateinit var myHandler: MyHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_handler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCountDown = view.findViewById(R.id.tv_count_down)
        btnStart = view.findViewById(R.id.btn_start)
        btnPause = view.findViewById(R.id.btn_pause)
        btnStop = view.findViewById(R.id.btn_stop)

        myHandler = MyHandler(1000L, 10_000L, { time ->
            tvCountDown.text = "剩余时间：${time / 1000}s"
        }, {
            tvCountDown.text = "倒计时结束！"
        })
        btnStart.setOnClickListener {
            myHandler.start()
        }
        btnPause.setOnClickListener {
            myHandler.pause()
        }
        btnStop.setOnClickListener {
            myHandler.stop()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myHandler.release()
    }

}