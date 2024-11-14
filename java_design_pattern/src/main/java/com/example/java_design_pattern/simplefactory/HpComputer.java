package com.example.java_design_pattern.simplefactory;

public class HpComputer extends Computer {
    @Override
    public void start() {
        System.out.println("惠普开机了");
    }
}
