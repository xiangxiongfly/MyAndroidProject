package com.example.java_design_pattern.template;

public class SpecialComputer extends AbsComputer {

    @Override
    protected boolean hasPassword() {
        return true;
    }

    @Override
    public void login() {
        System.out.println("人脸识别登录");
    }

    @Override
    public void run() {
        System.out.println("专用电脑 办公");
    }
}
