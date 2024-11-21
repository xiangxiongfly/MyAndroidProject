package com.example.java_design_pattern.abstractfactory;

public class Client {
    public static void main(String[] args) {
        LenovoComputerFactory factory = new LenovoComputerFactory();
        DesktopComputer desktopComputer = factory.createDesktopComputer();
        desktopComputer.start();
        LaptopComputer laptopComputer = factory.createLaptopComputer();
        laptopComputer.start();
    }
}
