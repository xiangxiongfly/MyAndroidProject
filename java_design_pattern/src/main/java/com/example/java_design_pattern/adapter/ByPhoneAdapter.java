package com.example.java_design_pattern.adapter;

public class ByPhoneAdapter extends LoginAdapter {

    public void loginByPhone(String phone) {
        System.out.println("手机号登录");
        login(phone, "***");
    }
}
