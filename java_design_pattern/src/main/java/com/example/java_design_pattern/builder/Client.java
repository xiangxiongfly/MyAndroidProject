package com.example.java_design_pattern.builder;

public class Client {
    public static void main(String[] args) {
        Builder builder = new DivComputerBuilder();
        Director director = new Director(builder);
        Computer computer = director.createComputer("技嘉", "amd", "金士顿");
        System.out.println(computer);
    }
}
