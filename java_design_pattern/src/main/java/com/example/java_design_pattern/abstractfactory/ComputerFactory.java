package com.example.java_design_pattern.abstractfactory;

public abstract class ComputerFactory {
    public abstract DesktopComputer createDesktopComputer();

    public abstract LaptopComputer createLaptopComputer();
}
