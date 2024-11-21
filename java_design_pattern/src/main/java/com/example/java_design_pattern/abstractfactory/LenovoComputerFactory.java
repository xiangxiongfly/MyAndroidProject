package com.example.java_design_pattern.abstractfactory;

public class LenovoComputerFactory extends ComputerFactory {
    @Override
    public DesktopComputer createDesktopComputer() {
        return new LenovoDesktopComputer();
    }

    @Override
    public LaptopComputer createLaptopComputer() {
        return new LenovoLaptopComputer();
    }
}
