package com.example.java_design_pattern.chainofresponsibility;

public class Client {
    public static void main(String[] args) {
        ConcreteHandlerA handler1 = new ConcreteHandlerA(1);
        ConcreteHandlerB handler2 = new ConcreteHandlerB(2);
        ConcreteHandlerC handler3 = new ConcreteHandlerC(3);
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
//        handler1.handleRequest(new Request(2));
        handler1.handleRequest(new Request(3));
    }
}
