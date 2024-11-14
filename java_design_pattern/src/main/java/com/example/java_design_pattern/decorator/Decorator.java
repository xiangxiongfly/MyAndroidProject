package com.example.java_design_pattern.decorator;

public abstract class Decorator extends Person {
    private Person person;

    public Decorator(Person person) {
        this.person = person;
    }

    @Override
    public void dressUp() {
        person.dressUp();
    }
}
