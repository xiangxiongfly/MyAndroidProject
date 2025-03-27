package com.xiangxiongfly.androidtools.okhttp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.xiangxiongfly.androidtools.R
import com.xiangxiongfly.androidtools.okhttp.bean.BeanFactory
import com.xiangxiongfly.androidtools.okhttp.bean.Page
import com.xiangxiongfly.androidtools.okhttp.bean.User
import com.xiangxiongfly.androidtools.okhttp.url.WanUrl
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.utils.okhttp.HttpClient
import com.xiangxiongfly.common.utils.okhttp.callback.Callback
import com.xiangxiongfly.common.utils.okhttp.exception.HttpException
import com.xiangxiongfly.common.utils.okhttp.manager.HttpConfig
import com.xiangxiongfly.common.utils.okhttp.manager.HttpOptions
import com.xiangxiongfly.common.utils.okhttp.manager.HttpParams

class OkHttpActivity : BaseActivity() {
    private lateinit var textView: TextView

    private val loadingDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)
        textView = findViewById(R.id.textView)

        HttpConfig.apply {
            addHeader("key111", "value111")
            addParams("key222", "value222")
        }

    }

    fun getClick(view: View) {
        val params = HttpParams().apply {
            addParam("cid", "294")
        }
        HttpClient.Builder()
            .get(WanUrl.list)
            .tag(null)
            .addHeaders("aaa", "bbb")
            .setParams(params)
            .executeAsync(object : Callback<BeanFactory<Page>> {
                override fun onStart() {
                    super.onStart()
                    showLoading()
                }

                override fun onSuccess(result: _root_ide_package_.com.xiangxiongfly.androidtools.okhttp.bean.BeanFactory<Page>) {
                    Log.e("TAG", "onSuccess: $result")
                    textView.text = result.toString()
                }

                override fun onError(e: HttpException) {
                    Log.e("TAG", "e: $e")
                    textView.text = e.msg
                }

                override fun onComplete() {
                    super.onComplete()
                    hideLoading()
                }
            })
    }

    fun postClick(view: View) {
        val params = HttpParams().apply {
            addParam("username", " ")
            addParam("password", " ")
        }
        HttpClient.Builder()
            .post(WanUrl.login)
            .setParams(params)
            .executeAsync(object :
                Callback<_root_ide_package_.com.xiangxiongfly.androidtools.okhttp.bean.BeanFactory<User>> {
                override fun onStart() {
                    super.onStart()
                    showLoading()
                }

                override fun onSuccess(result: _root_ide_package_.com.xiangxiongfly.androidtools.okhttp.bean.BeanFactory<User>) {
                    Log.e("TAG", "onSuccess: $result")
                    textView.text = result.toString()
                }

                override fun onError(e: HttpException) {
                    Log.e("TAG", "e: $e")
                    textView.text = e.msg
                }

                override fun onComplete() {
                    super.onComplete()
                    hideLoading()
                }
            })
    }

    fun postJsonClick(view: View) {
        val params = HttpParams().apply {
            addParam("username", " ")
            addParam("password", " ")
        }
        HttpClient.Builder()
            .post(WanUrl.login)
            .setParams(params)
            .setOptions(HttpOptions(isJson = true))
            .executeAsync(object :
                Callback<_root_ide_package_.com.xiangxiongfly.androidtools.okhttp.bean.BeanFactory<User>> {
                override fun onStart() {
                    super.onStart()
                    showLoading()
                }

                override fun onSuccess(result: _root_ide_package_.com.xiangxiongfly.androidtools.okhttp.bean.BeanFactory<User>) {
                    Log.e("TAG", "onSuccess: $result")
                    textView.text = result.toString()
                }

                override fun onError(e: HttpException) {
                    Log.e("TAG", "e: $e")
                    textView.text = e.msg
                }

                override fun onComplete() {
                    super.onComplete()
                    hideLoading()
                }
            })
    }

    private fun showLoading() {
        loadingDialog.show()
    }

    private fun hideLoading() {
        loadingDialog.hide()
    }
}