package com.example.java_sort;

public class Sort {

    public static void main(String[] args) {
        int[] oldArr = {3, 1, 4, 5, 2, 1};
        showArr("排序前：", oldArr);
        IArraySort sort = new BubbleSort();
        int[] newArr = sort.sort(oldArr);
        showArr("排序后：", newArr);
    }

    public static void showArr(String desc, int[] arr) {
        System.out.print(desc);
        for (int a : arr) {
            System.out.print(" " + a);
        }
        System.out.println();
    }
}
