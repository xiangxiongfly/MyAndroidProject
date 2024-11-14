package com.example.java_design_pattern.facade;

public class Client {
    public static void main(String[] args) {
        Facade facade=new Facade();
        // 先从缓存获取条件，然后再网络请求
        facade.sendData();
        // 先网络请求，然后再缓存数据
        facade.sendAndCache();
    }
}
