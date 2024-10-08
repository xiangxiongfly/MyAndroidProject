package com.example.java_sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort implements IArraySort {
    @Override
    public int[] sort(int[] srcArr) {
        int[] arr = Arrays.copyOf(srcArr, srcArr.length);

        int gap = 1;

        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }
        return arr;
    }
}

/*
分组

 */