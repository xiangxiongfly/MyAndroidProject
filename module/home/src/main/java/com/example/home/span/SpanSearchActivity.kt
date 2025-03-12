package com.example.home.span

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.TextAppearanceSpan
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.StyleRes
import com.example.base.BaseActivity
import com.example.base.KEY_TITLE
import com.example.home.R
import java.util.regex.Pattern

class SpanSearchActivity : BaseActivity() {
    private lateinit var editText: EditText
    private lateinit var textView: TextView

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SpanSearchActivity::class.java).apply {
                putExtra(KEY_TITLE, "搜索关键字变色")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_span_search)
        initView()
        initListener()
    }

    private fun initView() {
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)
    }

    private fun initListener() {
        val text = textView.text.toString()
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString()
                textView.text = matchKeyword(context, text, keyword, R.style.SearchStyle)
            }
        })
    }

    fun matchKeyword(
        context: Context,
        text: String,
        keyword: String,
        @StyleRes style: Int
    ): SpannableString {
        val span = SpannableString(text)
        val pattern = Pattern.compile(keyword)
        val matcher = pattern.matcher(span)
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            span.setSpan(
                TextAppearanceSpan(context, style),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return span
    }
}