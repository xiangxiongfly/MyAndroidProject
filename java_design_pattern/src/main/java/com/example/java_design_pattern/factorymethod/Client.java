package com.example.java_design_pattern.factorymethod;

public class Client {
    public static void main(String[] args) {
        FskComputerFactory factory = new FskComputerFactory();
        HpComputer hpComputer = factory.createComputer(HpComputer.class);
        hpComputer.start();
        LenovoComputer lenovoComputer = factory.createComputer(LenovoComputer.class);
        lenovoComputer.start();
    }
}
