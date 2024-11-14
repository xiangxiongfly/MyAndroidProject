package com.example.java_design_pattern.strategy;

public class Client {
    public static void main(String[] args) {
//        PriceCalculator calculator = new PriceCalculator();
//        int cost1 = calculator.calculatorPrice(PriceCalculator.TYPE_SUBWAY, 16);
//        System.out.println(cost1);
//        int cost2 = calculator.calculatorPrice(PriceCalculator.TYPE_BUS, 16);
//        System.out.println(cost2);

        Calculator calculator = new Calculator();
        calculator.setStrategy(new BusStrategy());
        int cost1 = calculator.calculatePrice(16);
        System.out.println(cost1);
        calculator.setStrategy(new SubwayStrategy());
        int cost2 = calculator.calculatePrice(16);
        System.out.println(cost2);
    }
}
