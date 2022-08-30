package com.example.home.recyclerview.divider;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView网格分割线
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int mDividerSize;
    private Paint mPaint;

    public GridItemDecoration(int color, int dividerSize) {
        mDividerSize = dividerSize;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
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
     * 是否最后一列
     */
    private boolean isLastColumn(RecyclerView parent, int position, int spanCount, int childCount) {
        if ((position + 1) % spanCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否最后一行
     */
    private boolean isLastRow(RecyclerView parent, int position, int spanCount, int childCount) {
        childCount = childCount - childCount % spanCount;
        if (position >= childCount) {
            return true;
        } else {
            return false;
        }
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
            outRect.set(0, 0, mDividerSize, 0);
        } else if (isLastColumn(parent, position, spanCount, childCount)) {
            //最后一列不绘制右边
            outRect.set(0, 0, 0, mDividerSize);
        } else {
            outRect.set(0, 0, mDividerSize, mDividerSize);
        }
    }

    /**
     * 绘制分割线
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    /**
     * 绘制垂直分割线
     */
    private void drawVertical(@NonNull Canvas c, @NonNull RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left = childView.getRight() + layoutParams.rightMargin;
            final int top = childView.getTop() - layoutParams.topMargin;
            final int right = left + mDividerSize;
            final int bottom = childView.getBottom() + layoutParams.bottomMargin;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 绘制水平分割线
     */
    private void drawHorizontal(@NonNull Canvas c, @NonNull RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left = childView.getLeft() - layoutParams.leftMargin;
            final int top = childView.getBottom() + layoutParams.bottomMargin;
            final int right = childView.getRight() + layoutParams.rightMargin + mDividerSize;
            final int bottom = top + mDividerSize;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }
}
