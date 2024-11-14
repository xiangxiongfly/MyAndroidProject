package com.example.java_design_pattern.template;

public class Client {
    public static void main(String[] args) {
        NormalComputer normalComputer = new NormalComputer();
        normalComputer.start();
        SpecialComputer specialComputer = new SpecialComputer();
        specialComputer.start();
    }
}
