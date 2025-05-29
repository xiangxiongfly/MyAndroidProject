package com.xiangxiongfly.androidtools.countdown.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.androidtools.countdown.utils.MyCountDownTimer
import com.xiangxiongfly.common.base.BaseFragment

class CountDownTimerFragment : BaseFragment() {
    private lateinit var tvCountDown: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    private lateinit var countDownTimer: MyCountDownTimer

    companion object {
        @JvmStatic
        fun newInstance() = CountDownTimerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_countdown_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCountDown = view.findViewById(R.id.tv_count_down)
        btnStart = view.findViewById(R.id.btn_start)
        btnStop = view.findViewById(R.id.btn_stop)

        countDownTimer = MyCountDownTimer(
            1000L, 10_000L,
            { time ->
                tvCountDown.text = "剩余时间：${time / 1000}s"
            },
            {
                tvCountDown.text = "倒计时结束！"
            }
        )
        btnStart.setOnClickListener {
            countDownTimer.start()
        }

        btnStop.setOnClickListener {
            countDownTimer.stop()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.release()
    }
}