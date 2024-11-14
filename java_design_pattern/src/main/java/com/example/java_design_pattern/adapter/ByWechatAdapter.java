package com.example.java_design_pattern.adapter;

public class ByWechatAdapter extends LoginAdapter {
    public void loginByWechat(String tempToken) {
        System.out.println("微信登录");
        login(getWechatName(tempToken), getTempPassword(tempToken));
    }

    private String getWechatName(String tempToken) {
        return "微信昵称";
    }

    private String getTempPassword(String tempToken) {
        return "***";
    }
}
