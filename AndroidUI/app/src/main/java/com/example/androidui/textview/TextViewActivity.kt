package com.example.androidui.textview

import android.os.Bundle
import com.example.androidui.R
import com.example.core.base.BaseActivity

class TextViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)
//        val textView = findViewById<TextView>(R.id.text_view)

//        textView.isSelected = true

//        textView.setTextColor(Color.parseColor("#FF0000"))
//        textView.setTextColor(ContextCompat.getColor(context, R.color.red))

//        textView.setTextSize(16F)
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)

//        textView.setTypeface(null, Typeface.BOLD)
//        textView.setTypeface(Typeface.MONOSPACE)

//        textView.setLineSpacing(4F, 2F)

//        textView.setMaxLines(2)
//        textView.setEllipsize(TextUtils.TruncateAt.END)

//        val spannable = SpannableString("helloTextView!")
//        spannable.setSpan(ForegroundColorSpan(Color.RED), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        spannable.setSpan(object : ClickableSpan() {
//            override fun onClick(widget: View) {
//                Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
//            }
//        }, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        textView.setText(spannable)

//        textView.setText(Html.fromHtml("<b>Bold</b> <i>Italic</i> <font color='red'>Red</font>"))

    }
}