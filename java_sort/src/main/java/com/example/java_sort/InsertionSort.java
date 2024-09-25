package com.example.java_sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort implements IArraySort {

    @Override
    public int[] sort(int[] srcArr) {
        int[] arr = Arrays.copyOf(srcArr, srcArr.length);
        // 外层循环，第一个元素arr[0]默认为有序的，因此从第二个元素开始遍历
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i]; // 缓存指定元素
            int j = i;
            // 内层循环
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1]; // 元素右移
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }
}