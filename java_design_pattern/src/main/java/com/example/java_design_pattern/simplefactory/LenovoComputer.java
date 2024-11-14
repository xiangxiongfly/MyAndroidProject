package com.example.java_design_pattern.simplefactory;

public class LenovoComputer extends Computer {
    @Override
    public void start() {
        System.out.println("联想开机了");
    }
}