package com.example.java_design_pattern.factorymethod;

public class FskComputerFactory extends ComputerFactory {
    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer = null;
        String clzName = clz.getName();
        try {
            computer = (Computer) Class.forName(clzName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
