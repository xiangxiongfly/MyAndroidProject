package com.xiangxiongfly.androidstatusbar

import android.os.Bundle
import android.view.View
import com.xiangxiongfly.androidstatusbar.base.BaseActivity
import com.xiangxiongfly.androidstatusbar.exts.actionStart
import com.xiangxiongfly.androidstatusbar.immersion_status_bar.Immersion1Activity
import com.xiangxiongfly.androidstatusbar.immersion_status_bar.Immersion2Activity
import com.xiangxiongfly.androidstatusbar.immersion_status_bar.Immersion3Activity
import com.xiangxiongfly.androidstatusbar.immersion_status_bar.Immersion4Activity
import com.xiangxiongfly.androidstatusbar.immersion_status_bar.Immersion5Activity
import com.xiangxiongfly.androidstatusbar.statusbar.FullScreen2Activity
import com.xiangxiongfly.androidstatusbar.statusbar.FullScreenActivity
import com.xiangxiongfly.androidstatusbar.statusbar.OperateStatusBar3Activity
import com.xiangxiongfly.androidstatusbar.theme.FullScreenThemeActivity
import com.xiangxiongfly.androidstatusbar.theme.LightStatusBarThemeActivity
import com.xiangxiongfly.androidstatusbar.theme.NoActionBarThemeActivity
import com.xiangxiongfly.androidstatusbar.theme.TransparentThemeActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_no_action_bar -> actionStart<NoActionBarThemeActivity>()
            R.id.btn_full_screen -> actionStart<FullScreenThemeActivity>()
            R.id.btn_light -> actionStart<LightStatusBarThemeActivity>()
            R.id.btn_translucent -> actionStart<TransparentThemeActivity>()

            R.id.btn_opera_full_screen -> actionStart<FullScreenActivity>()
            R.id.btn_opera_full_screen2 -> actionStart<FullScreen2Activity>()
            R.id.btn_opera_status_bar3 -> actionStart<OperateStatusBar3Activity>()

            R.id.btn_immersion_status_bar1 -> actionStart<Immersion1Activity>()
            R.id.btn_immersion_status_bar2 -> actionStart<Immersion2Activity>()
            R.id.btn_immersion_status_bar3 -> actionStart<Immersion3Activity>()
            R.id.btn_immersion_status_bar4 -> actionStart<Immersion4Activity>()
            R.id.btn_immersion_status_bar5 -> actionStart<Immersion5Activity>()
        }
    }
}