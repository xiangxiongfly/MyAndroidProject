package com.example.java_design_pattern.adapter;

public class LoginAdapter implements Adapter {
    @Override
    public void login(String username, String password) {
        System.out.println("登录成功，" + username + "欢迎您");
    }
}
