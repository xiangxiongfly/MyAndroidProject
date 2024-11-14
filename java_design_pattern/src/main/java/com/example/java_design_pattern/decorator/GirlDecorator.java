package com.example.java_design_pattern.decorator;

public class GirlDecorator extends Decorator {
    public GirlDecorator(Person person) {
        super(person);
    }

    @Override
    public void dressUp() {
        takeStocking();
        super.dressUp();
        takeHighheeledShoes();
    }

    private void takeStocking() {
        System.out.println("穿长袜");
    }

    private void takeHighheeledShoes() {
        System.out.println("穿高跟鞋");
    }
}
