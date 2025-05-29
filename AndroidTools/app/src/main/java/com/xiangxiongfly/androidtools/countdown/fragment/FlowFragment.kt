package com.xiangxiongfly.androidtools.countdown.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.androidtools.countdown.utils.MyFlow
import com.xiangxiongfly.common.base.BaseFragment

class FlowFragment : BaseFragment() {
    private lateinit var tvCountDown: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    private lateinit var myFlow: MyFlow

    companion object {
        @JvmStatic
        fun newInstance() = FlowFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_flow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCountDown = view.findViewById(R.id.tv_count_down)
        btnStart = view.findViewById(R.id.btn_start)
        btnStop = view.findViewById(R.id.btn_stop)

        myFlow = MyFlow(
            1000L, 10_000L,
            { time ->
                tvCountDown.text = "剩余时间：${time / 1000}s"
            },
            {
                tvCountDown.text = "倒计时结束！"
            },
            lifecycleScope
        )
        btnStart.setOnClickListener {
            myFlow.start()
        }
        btnStop.setOnClickListener {
            myFlow.stop()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myFlow.release()
    }
}