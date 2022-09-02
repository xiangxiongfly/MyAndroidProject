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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView网格分割线
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    //Drawable分割线
    private Drawable mDivider;
    private final int mDividerWidthSize;
    private final int mDividerHeightSize;

    /**
     * 添加分割线方式一
     *
     * @param color       分割线颜色
     * @param dividerSize 分割线尺寸
     */
    public GridItemDecoration(int color, int dividerSize) {
        mDividerWidthSize = dividerSize;
        mDividerHeightSize = dividerSize;
        ColorDrawable shapeDrawable = new ColorDrawable(color);
        mDivider = shapeDrawable;
    }

    public GridItemDecoration(Context context, @DrawableRes int drawableId) {
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerHeightSize = mDivider.getIntrinsicHeight();
        mDividerWidthSize = mDivider.getIntrinsicWidth();
    }

    /**
     * 获取RecyclerView列数
     */
    private int getSpanCount(RecyclerView parent) {
        int spanCount;
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        spanCount = layoutManager.getSpanCount();
        return spanCount;
    }

    /**
     * 设置分割线大小
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int position = parent.getChildAdapterPosition(view);
        if (isLastRow(parent, position, spanCount, childCount)) {
            //最后一行不绘制底部
            outRect.set(0, 0, mDividerWidthSize, 0);
        } else if (isLastColumn(parent, position, spanCount, childCount)) {
            //最后一列不绘制右边
            outRect.set(0, 0, 0, mDividerHeightSize);
        } else {
            outRect.set(0, 0, mDividerWidthSize, mDividerHeightSize);
        }
    }

    /**
     * 是否最后一列
     */
    private boolean isLastColumn(RecyclerView parent, int position, int spanCount, int childCount) {
        if ((position + 1) % spanCount == 0) { //最后一列不需要绘制右边
            return true;
        }
        return false;
    }

    /**
     * 是否最后一行
     */
    private boolean isLastRow(RecyclerView parent, int position, int spanCount, int childCount) {
        childCount = childCount - childCount % spanCount;
        if (position >= childCount) { //最后一行不需要绘制底部
            return true;
        }
        return false;
    }

    /**
     * 绘制分割线
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    /**
     * 绘制垂直分割线
     */
    private void drawVertical(@NonNull Canvas canvas, @NonNull RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left = childView.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerWidthSize;
            final int top = childView.getTop() - layoutParams.topMargin;
            final int bottom = childView.getBottom() + layoutParams.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }

    /**
     * 绘制水平分割线
     */
    private void drawHorizontal(@NonNull Canvas canvas, @NonNull RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left = childView.getLeft() - layoutParams.leftMargin;
            final int right = childView.getRight() + layoutParams.rightMargin + mDividerWidthSize;
            final int top = childView.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeightSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }
}
