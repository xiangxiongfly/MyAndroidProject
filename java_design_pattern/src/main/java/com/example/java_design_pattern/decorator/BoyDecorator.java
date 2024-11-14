package com.example.java_design_pattern.decorator;

public class BoyDecorator extends Decorator {

    public BoyDecorator(Person person) {
        super(person);
    }

    @Override
    public void dressUp() {
        takeNecktie();
        super.dressUp();
        takeLeatherShoes();
    }

    private void takeNecktie() {
        System.out.println("打领带");
    }

    private void takeLeatherShoes() {
        System.out.println("穿皮鞋");
    }
}
