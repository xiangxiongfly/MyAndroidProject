package com.example.java_sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort implements IArraySort {

    @Override
    public int[] sort(int[] srcArr) {
        int[] arr = Arrays.copyOf(srcArr, srcArr.length);
        // 外层循环控制排序轮数
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环，两两相邻比较
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}