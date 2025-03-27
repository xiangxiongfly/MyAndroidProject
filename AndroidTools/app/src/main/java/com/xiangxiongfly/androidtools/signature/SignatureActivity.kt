package com.xiangxiongfly.androidtools.signature

import android.os.Bundle
import android.view.View
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.widgets.view.SignatureView

class SignatureActivity : BaseActivity() {
    private lateinit var signatureView: SignatureView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signature)
        signatureView = findViewById(R.id.signature_view)
    }

    fun onSave1(view: View) {
        val imageDir = "${this.cacheDir}/signature"
        val imageName = "${System.currentTimeMillis()}.png"
        signatureView.save(imageDir, imageName)
    }

    fun onSave2(view: View) {
        val imageDir = "${this.cacheDir}/signature"
        val imageName = "${System.currentTimeMillis()}.png"
        signatureView.save(imageDir, imageName, true, 10)
    }

    fun onClear(view: View) {
        signatureView.clear()
    }
}