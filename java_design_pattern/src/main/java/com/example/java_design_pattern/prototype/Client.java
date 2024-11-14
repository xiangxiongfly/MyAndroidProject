package com.example.java_design_pattern.prototype;

public class Client {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("小明");
        p.setAge(18);
        p.setAddress("广东", "广州");

        Person p1 = p.clone();
        p1.setName("小白");
        p1.setAddress("湖南", "长沙");
        Person p2 = p.clone();
        p2.setName("小黑");
        p2.setAddress("安徽", "合肥");

        p.show();
        p1.show();
        p2.show();
    }
}