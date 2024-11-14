package com.example.java_design_pattern.simplefactory;

public class Client {
    public static void main(String[] args) {
        Computer hp = ComputerFactory.createComputer("hp");
        hp.start();
        Computer lenovo = ComputerFactory.createComputer("lenovo");
        lenovo.start();
    }
}
