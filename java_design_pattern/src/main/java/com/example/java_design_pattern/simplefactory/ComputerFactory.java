package com.example.java_design_pattern.simplefactory;

public class ComputerFactory {
    public static Computer createComputer(String computer) {
        if ("hp".equals(computer)) {
            return new HpComputer();
        } else if ("lenovo".equals(computer)) {
            return new LenovoComputer();
        } else {
            return null;
        }
    }
}
