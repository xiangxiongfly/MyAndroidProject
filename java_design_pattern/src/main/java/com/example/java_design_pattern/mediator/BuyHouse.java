package com.example.java_design_pattern.mediator;

import static com.example.java_design_pattern.mediator.Const.TYPE_BUY;

public class BuyHouse extends Colleague {
    private String name;

    public BuyHouse(String name, Mediator mediator) {
        super(mediator);
        this.name = name;
    }

    public void receiveMessage(String message) {
        System.out.println(name + "收到消息:" + message);
    }

    public void sendMessage(String message) {
        System.out.println(name + "发送消息:" + message);
        mediator.notify(message, TYPE_BUY);
    }
}
