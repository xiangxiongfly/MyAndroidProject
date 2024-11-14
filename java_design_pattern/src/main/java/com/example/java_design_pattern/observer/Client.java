package com.example.java_design_pattern.observer;

public class Client {
    public static void main(String[] args) {
        Subscription subscription = new Subscription();
        User u1 = new User("小明");
        User u2 = new User("小白");
        User u3 = new User("小黑");
        subscription.attach(u1);
        subscription.attach(u2);
        subscription.attach(u3);
        subscription.notify("hello");
    }
}
