package com.example.java_design_pattern.factorymethod;

public abstract class ComputerFactory {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}
