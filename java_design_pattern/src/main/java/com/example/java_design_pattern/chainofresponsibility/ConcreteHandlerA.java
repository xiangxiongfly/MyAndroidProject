package com.example.java_design_pattern.chainofresponsibility;

public class ConcreteHandlerA extends Handler {

    public ConcreteHandlerA(int level) {
        super(level);
    }

    @Override
    public void response(Request request) {
        System.out.println("处理器A进行处理");
    }
}
