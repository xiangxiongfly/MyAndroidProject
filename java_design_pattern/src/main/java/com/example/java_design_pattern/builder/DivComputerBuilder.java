package com.example.java_design_pattern.builder;

public class DivComputerBuilder extends Builder {
    private Computer computer = new Computer();

    @Override
    public void buildMainboard(String mainboard) {
        computer.setMainboard(mainboard);
    }

    @Override
    public void buildCpu(String cpu) {
        computer.setCpu(cpu);
    }

    @Override
    public void buildMemory(String memory) {
        computer.setMemory(memory);
    }

    @Override
    public Computer create() {
        return computer;
    }
}
