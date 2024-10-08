package com.example.java_sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort implements IArraySort {

    @Override
    public int[] sort(int[] srcArr) {
        int[] arr = Arrays.copyOf(srcArr, srcArr.length);
        // 假设下标为0表示有序序列，下标为1到末尾是未排序序列
        // 外层循环：遍历未排序序列
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1; // 有序序列最右边的元素
            if (arr[i] < arr[i - 1]) {
                int temp = arr[i]; // 记录要插入的元素，未排序序列最左边的元素
                // 内层循环：遍历有序序列
                for (; j >= 0 && temp < arr[j]; --j) {
                    arr[j + 1] = arr[j]; // 元素右移
                }
                arr[j + 1] = temp;
            }
            Sort.showArr("第" + (i) + "轮：", arr);
        }
        return arr;
    }
}