package com.example.java_design_pattern.template;

public abstract class AbsComputer {

    public final void start() {
        powerOn();
        loadSystem();
        if (hasPassword()) {
            login();
        }
        run();
    }

    public void powerOn() {
        System.out.println("连接电源");
    }

    public void loadSystem() {
        System.out.println("加载系统");
    }

    protected boolean hasPassword() {
        return false;
    }

    public void login() {
        System.out.println("登录");
    }

    public abstract void run();

}
