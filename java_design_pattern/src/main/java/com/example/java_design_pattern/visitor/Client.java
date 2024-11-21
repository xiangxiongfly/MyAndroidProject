package com.example.java_design_pattern.visitor;

public class Client {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ConcreteVisitor());
    }
}
