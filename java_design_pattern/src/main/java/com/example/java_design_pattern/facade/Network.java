package com.example.java_design_pattern.facade;

public class Network {
    public void request(){
        System.out.println("发送请求");
    }

    public void receiveData(){
        System.out.println("接收数据");
    }
}
