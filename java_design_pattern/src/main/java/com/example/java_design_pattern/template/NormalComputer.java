package com.example.java_design_pattern.template;

public class NormalComputer extends AbsComputer {

    @Override
    protected boolean hasPassword() {
        return true;
    }

    @Override
    public void login() {
        System.out.println("密码登录");
    }

    @Override
    public void run() {
        System.out.println("普通电脑 玩游戏");
    }
}
