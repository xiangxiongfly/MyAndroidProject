package com.example.home.recyclerview.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView线性布局的分割线
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL_LIST = RecyclerView.HORIZONTAL;
    public static final int VERTICAL_LIST = RecyclerView.VERTICAL;

    //RecyclerView布局方向
    private int mOrientation = VERTICAL_LIST;
    //分割线尺寸
    private int mDividerSize = 1;
    //Drawable分割线
    private final Drawable mDivider;

    /**
     * 添加分割线方式一
     *
     * @param context     Context
     * @param orientation 方向
     * @param drawableId  Drawable图片
     */
    public LinearItemDecoration(Context context, int orientation, @DrawableRes int drawableId) {
        mOrientation = orientation;
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerSize = mDivider.getIntrinsicHeight();
    }

    /**
     * 添加分割线方式二
     *
     * @param orientation  方向
     * @param dividerColor 分割线颜色
     * @param dividerSize  分割线尺寸
     */
    public LinearItemDecoration(int orientation, int dividerColor, int dividerSize) {
        mOrientation = orientation;
        mDivider = new ColorDrawable(dividerColor);
        mDividerSize = dividerSize;
    }

    /**
     * 设置分割线大小
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDividerSize);
        } else {
            outRect.set(0, 0, mDividerSize, 0);
        }
    }

    /**
     * 绘制分割线
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 垂直方向分割线绘制
     */
    private void drawVertical(@NonNull Canvas canvas, @NonNull RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) { //最后一条不显示
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int top = childView.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }

    /**
     * 水平方向分割线绘制
     */
    private void drawHorizontal(@NonNull Canvas canvas, @NonNull RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) { //最后一条不显示
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left = childView.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }
}
