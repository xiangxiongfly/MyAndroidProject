package com.example.home.span

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.home.R
import com.xiangxiongfly.common.base.BaseActivity
import com.xiangxiongfly.common.base.KEY_TITLE

class SpanSimpleActivity : BaseActivity() {
    private lateinit var listView: ListView

    private val mList = arrayListOf<CharSequence>()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SpanSimpleActivity::class.java).apply {
                putExtra(KEY_TITLE, "SpannableString基本使用")
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_span_simple)
        initView()
        initFlags()
        initWhat()
        initHtml()
        initLv()
    }

    private fun initView() {
        listView = findViewById(R.id.listView)
    }

    private fun initFlags() {
        mList.add(
            SpannableStringBuilder("123456789").apply {
                setSpan(ForegroundColorSpan(Color.RED), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                insert(1, "A")
            }
        )

        mList.add(
            SpannableStringBuilder("123456789").apply {
                setSpan(ForegroundColorSpan(Color.RED), 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                insert(1, "A")
            }
        )

        mList.add(
            SpannableStringBuilder("123456789").apply {
                setSpan(ForegroundColorSpan(Color.RED), 1, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                insert(1, "A")
            }
        )

        mList.add(
            SpannableStringBuilder("123456789").apply {
                setSpan(ForegroundColorSpan(Color.RED), 1, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                insert(1, "A")
            }
        )
    }

    private fun initWhat() {
        mList.add(
            SpannableString("设置前景色").apply {
                setSpan(ForegroundColorSpan(Color.GREEN), 1, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableStringBuilder("追加字符串").apply {
                val text = "fuck!"
                append(text)
                setSpan(ForegroundColorSpan(Color.RED), 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置背景色").apply {
                setSpan(BackgroundColorSpan(Color.GREEN), 1, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置下划线").apply {
                setSpan(UnderlineSpan(), 1, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置删除线").apply {
                setSpan(StrikethroughSpan(), 1, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置上下标:y=x3+An").apply {
                //设置上标
                setSpan(SuperscriptSpan(), 9, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                //设置下标
                setSpan(SubscriptSpan(), 12, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("超级链接：电话").apply {
                setSpan(URLSpan("tel:12345678911"), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("超级链接：网络").apply {
                setSpan(URLSpan("http://www.baidu.com"), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(ForegroundColorSpan(Color.RED), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("超级链接：短信").apply {
                setSpan(URLSpan("sms:12345678912"), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(ForegroundColorSpan(Color.RED), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置字体样式：正常、粗体、斜体、粗斜体").apply {
                setSpan(StyleSpan(Typeface.NORMAL), 7, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(StyleSpan(Typeface.BOLD), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(StyleSpan(Typeface.ITALIC), 13, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(StyleSpan(Typeface.BOLD_ITALIC), 16, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置字体：default、default-bold、monospace、serif、sans-serif").apply {
                setSpan(TypefaceSpan("default"), 5, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(TypefaceSpan("default-bold"), 13, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(TypefaceSpan("monospace"), 26, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(TypefaceSpan("serif"), 36, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(TypefaceSpan("sans-serif"), 42, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置字体大小（绝对值：单位：像素、单位：像素）").apply {
                setSpan(AbsoluteSizeSpan(10, true), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(AbsoluteSizeSpan(30, true), 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("设置字体大小（相对值：单位：像素、单位：像素）").apply {
                setSpan(RelativeSizeSpan(0.5F), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(RelativeSizeSpan(2F), 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        mList.add(
            SpannableString("后面添加图片：#").apply {
                setSpan(
                    ImageSpan(mContext, R.mipmap.ic_launcher),
                    7, 8,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        )
        mList.add(
            SpannableString("我的中#间添加图片").apply {
                setSpan(
                    ImageSpan(mContext, R.mipmap.ic_launcher),
                    3, 4,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        )
        mList.add(
            SpannableString("图片点击事件#").apply {
                setSpan(
                    ImageSpan(mContext, R.mipmap.ic_launcher),
                    6,
                    7,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show()
                        }
                    }, 6, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        )
        mList.add(
            SpannableString("复杂的点击效果1").apply {
                setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show()
                    }
                }, 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )

        mList.add(
            SpannableString("复杂的点击效果2").apply {
                setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.isUnderlineText = false
                    }
                }, 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
    }

    private fun initHtml() {
        val html: String = "HTML:<img src=\"${R.mipmap.ic_launcher}\">"
        val imageGetter = CustomImageGetter(mContext, 0, 0)
        val htmlString = Html.fromHtml(html, imageGetter, null)
        mList.add(htmlString)
    }

    class CustomImageGetter(
        val mContext: Context,
        private val width: Int,
        private val height: Int
    ) : Html.ImageGetter {
        override fun getDrawable(source: String): Drawable {
            val drawableRes = source.toInt()
            val drawable = ContextCompat.getDrawable(mContext, drawableRes)
            drawable?.let {
                it.setBounds(
                    0,
                    0,
                    if (width != 0) width else it.intrinsicWidth,
                    if (height != 0) height else it.intrinsicHeight
                )
            }
            return drawable!!
        }
    }

    private fun initLv() {
        val adapter = TextAdapter(this, mList)
        listView.adapter = adapter
    }

    class TextAdapter(val mContext: Context, val mList: ArrayList<CharSequence>) : BaseAdapter() {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

        override fun getCount(): Int = mList.size

        override fun getItem(position: Int): CharSequence = mList[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val itemView: View
            val viewHolder: ViewHolder
            if (convertView == null) {
                itemView =
                    layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
                val textView = itemView.findViewById<TextView>(android.R.id.text1)
                textView.movementMethod = LinkMovementMethod.getInstance()
                viewHolder = ViewHolder(textView)
                itemView.tag = viewHolder
            } else {
                itemView = convertView
                viewHolder = itemView.tag as ViewHolder
            }
            viewHolder.textView.text = mList[position]
            return itemView
        }

        class ViewHolder(var textView: TextView)
    }
}
