package com.example.java_design_pattern.adapter;

public class Client {
    public static void main(String[] args) {
        // 默认登录
        LoginAdapter loginService = new LoginAdapter();
        loginService.login("小明", "123456");
        // 手机号登录
        ByPhoneAdapter loginByPhone = new ByPhoneAdapter();
        loginByPhone.loginByPhone("10086");
        // 微信登录
        ByWechatAdapter loginByWechat = new ByWechatAdapter();
        loginByWechat.loginByWechat("666");
    }
}
