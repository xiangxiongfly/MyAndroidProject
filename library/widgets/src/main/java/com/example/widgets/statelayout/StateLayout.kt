package com.example.widgets.statelayout

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.ArrayMap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.example.widgets.R

class StateLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    // empty布局资源
    @LayoutRes
    private var emptyLayoutId = View.NO_ID
        get() = if (field == View.NO_ID) StateConfig.emptyLayoutId else field

    // loading布局资源
    @LayoutRes
    private var loadingLayoutId = View.NO_ID
        get() = if (field == View.NO_ID) StateConfig.loadingLayoutId else field

    // error布局资源
    @LayoutRes
    private var errorLayoutId = View.NO_ID
        get() = if (field == View.NO_ID) StateConfig.errorLayoutId else field

    // 保存状态
    private val stateViewMap = ArrayMap<State, View>()

    // 当前状态
    private var currentState = State.CONTENT

    // 需要设置点击事件的id
    private var retryIds: IntArray? = null
        get() = field ?: StateConfig.retryIds

    private var mOnStateChangeListener: OnStateChangeListener? = null
        get() = field ?: StateConfig.getOnStateChangeListener()

    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StateLayout)
        emptyLayoutId = a.getResourceId(R.styleable.StateLayout_empty_layout, View.NO_ID)
        loadingLayoutId = a.getResourceId(R.styleable.StateLayout_loading_layout, View.NO_ID)
        errorLayoutId = a.getResourceId(R.styleable.StateLayout_error_layout, View.NO_ID)
        a.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount != 1) {
            throw IllegalStateException("StateLayout必须且只能有一个子View")
        }
        if (stateViewMap.size == 0) {
            val view = getChildAt(0)
            setContentView(view)
        }
    }

    /**
     * 设置内容布局
     */
    private fun setContentView(contentView: View) {
        stateViewMap[State.CONTENT] = contentView
    }

    /**
     * 设置点击事件
     */
    fun setRetryIds(@IdRes vararg ids: Int) {
        retryIds = ids
    }

    /**
     * 设置状态变化监听
     */
    fun setOnStateChangeListener(listener: OnStateChangeListener) {
        mOnStateChangeListener = listener
    }

    /**
     * 显示内容布局
     */
    fun showContent() {
        showState(State.CONTENT)
    }

    /**
     * 显示加载布局
     */
    fun showLoading() {
        showState(State.LOADING)
    }

    /**
     * 显示失败布局
     */
    fun showError() {
        showState(State.ERROR)
    }

    /**
     * 显示空布局
     */
    fun showEmpty() {
        showState(State.EMPTY)
    }

    /**
     * 显示视图
     */
    private fun showState(state: State) {
        if (currentState == state) {
            return
        }
        val stateView = getStateView(state)
        hideOtherViews(state)
        showStateView(this, stateView, state)
        mOnStateChangeListener?.onStateChange(state)
        currentState = state
    }

    /**
     * 隐藏其他视图
     */
    private fun hideOtherViews(key: State) {
        for ((state, stateView) in stateViewMap) {
            if (state != key) {
                stateView.visibility = View.GONE
            }
        }
    }

    /**
     * 获取状态视图
     */
    private fun getStateView(state: State): View {
        val view = stateViewMap[state]
        if (view != null) {
            return view
        }
        val layoutId = when (state) {
            State.EMPTY -> emptyLayoutId
            State.ERROR -> errorLayoutId
            State.LOADING -> loadingLayoutId
            State.CONTENT -> View.NO_ID
        }
        if (layoutId == View.NO_ID) {
            when (state) {
                State.ERROR -> throw Resources.NotFoundException("请设置errorLayout")
                State.EMPTY -> throw Resources.NotFoundException("请设置emptyLayout")
                State.LOADING -> throw Resources.NotFoundException("请设置loadingLayout")
                State.CONTENT -> throw Resources.NotFoundException("请设置contentView")
            }
        }
        val stateView = LayoutInflater.from(context).inflate(layoutId, this, false)
        stateViewMap[state] = stateView
        return stateView
    }

    /**
     * 显示视图
     */
    private fun showStateView(container: StateLayout, stateView: View, state: State) {
        if (container.indexOfChild(stateView) != -1) {
            stateView.visibility = View.VISIBLE
        } else {
            if (state == State.EMPTY || state == State.ERROR) {
                retryIds?.let {
                    for (id in it) {
                        stateView.findViewById<View>(id).setOnClickListener {
                            showLoading()
                        }
                    }
                }
            }
            container.addView(stateView)
        }
    }
}