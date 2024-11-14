package com.example.java_design_pattern.chainofresponsibility;

public class ConcreteHandlerC extends Handler {

    public ConcreteHandlerC(int level) {
        super(level);
    }

    @Override
    public void response(Request request) {
        System.out.println("处理器C进行处理");
    }
}
