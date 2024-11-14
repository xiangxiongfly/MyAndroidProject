package com.example.java_design_pattern.absfactory;

public abstract class ComputerFactory {
    public abstract DesktopComputer createDesktopComputer();

    public abstract LaptopComputer createLaptopComputer();
}
