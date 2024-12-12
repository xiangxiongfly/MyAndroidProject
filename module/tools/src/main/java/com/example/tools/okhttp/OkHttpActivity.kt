package com.example.tools.okhttp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.base.BaseActivity
import com.example.base.utils.okhttp.HttpClient
import com.example.base.utils.okhttp.callback.Callback
import com.example.base.utils.okhttp.exception.HttpException
import com.example.base.utils.okhttp.manager.HttpConfig
import com.example.base.utils.okhttp.manager.HttpOptions
import com.example.base.utils.okhttp.manager.HttpParams
import com.example.tools.R
import com.example.tools.okhttp.bean.BaseBean
import com.example.tools.okhttp.bean.Page
import com.xiangxiongfly.common.bean.User

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
            .executeAsync(object : Callback<BaseBean<Page>> {
                override fun onStart() {
                    super.onStart()
                    showLoading()
                }

                override fun onSuccess(result: BaseBean<Page>) {
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
            .executeAsync(object : Callback<BaseBean<User>> {
                override fun onStart() {
                    super.onStart()
                    showLoading()
                }

                override fun onSuccess(result: BaseBean<User>) {
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
            .executeAsync(object : Callback<BaseBean<User>> {
                override fun onStart() {
                    super.onStart()
                    showLoading()
                }

                override fun onSuccess(result: BaseBean<User>) {
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