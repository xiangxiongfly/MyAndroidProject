package com.example.java_design_pattern.chainofresponsibility;

public class ConcreteHandlerB extends Handler {

    public ConcreteHandlerB(int level) {
        super(level);
    }

    @Override
    public void response(Request request) {
        System.out.println("处理器B进行处理");
    }
}
