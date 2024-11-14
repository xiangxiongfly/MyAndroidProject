package com.example.java_design_pattern.facade;

public class Database {

    public void connect(){
        System.out.println("连接数据库");
    }

    public void getData(){
        System.out.println("读取数据");
    }

    public void saveData(){
        System.out.println("缓存数据");
    }
}
