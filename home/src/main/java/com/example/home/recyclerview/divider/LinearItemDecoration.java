package com.example.home.recyclerview.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
    public static final int HORIZONTAL = RecyclerView.HORIZONTAL;
    public static final int VERTICAL = RecyclerView.VERTICAL;

    //RecyclerView布局方向
    private int mOrientation = VERTICAL;
    //分割线大小
    private int mDividerSize = 1;
    //Drawable分割线
    private Drawable mDivider;

    private Paint mPaint;

    /**
     * 添加分割线方式一
     *
     * @param orientation 方向
     * @param color       颜色
     * @param dividerSize 分割线大小
     */
    public LinearItemDecoration(int orientation, int color, int dividerSize) {
        mOrientation = orientation;
        mDividerSize = dividerSize;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 添加分割线方式二
     *
     * @param context     Context
     * @param orientation 方向
     * @param drawableId  Drawable图片
     */
    public LinearItemDecoration(Context context, int orientation, @DrawableRes int drawableId) {
        mOrientation = orientation;
        mDivider = ContextCompat.getDrawable(context, drawableId);
        if (mDivider != null) {
            mDividerSize = mDivider.getIntrinsicHeight();
        } else {
            mDividerSize = 0;
        }
    }

    /**
     * 设置分割线大小
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation == VERTICAL) {
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
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 垂直方向分割线绘制
     */
    private void drawVertical(@NonNull Canvas c, @NonNull RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int top = childView.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerSize;
            if (mDivider == null) {
                c.drawRect(left, top, right, bottom, mPaint);
            } else {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    /**
     * 水平方向分割线绘制
     */
    private void drawHorizontal(@NonNull Canvas c, @NonNull RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left = childView.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerSize;
            if (mDivider == null) {
                c.drawRect(left, top, right, bottom, mPaint);
            } else {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
