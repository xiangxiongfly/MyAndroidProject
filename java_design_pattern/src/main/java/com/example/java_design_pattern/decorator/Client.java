package com.example.java_design_pattern.decorator;

public class Client {
    public static void main(String[] args) {
        Person boy = new Boy();
        BoyDecorator boyDecorator = new BoyDecorator(boy);
        boyDecorator.dressUp();

        Person girl = new Girl();
        GirlDecorator girlDecorator = new GirlDecorator(girl);
        girlDecorator.dressUp();
    }
}
