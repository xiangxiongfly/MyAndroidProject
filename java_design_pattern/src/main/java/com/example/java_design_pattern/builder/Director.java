package com.example.java_design_pattern.builder;

public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Computer createComputer(String mainboard, String cpu, String memory) {
        builder.buildMainboard(mainboard);
        builder.buildCpu(cpu);
        builder.buildMemory(memory);
        Computer computer = builder.create();
        return computer;
    }
}
