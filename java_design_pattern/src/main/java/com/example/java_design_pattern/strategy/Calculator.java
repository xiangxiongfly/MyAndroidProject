package com.example.java_design_pattern.strategy;

public class Calculator {
    Strategy strategy = null;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calculatePrice(int km) {
        return strategy.calculatePrice(km);
    }
}
