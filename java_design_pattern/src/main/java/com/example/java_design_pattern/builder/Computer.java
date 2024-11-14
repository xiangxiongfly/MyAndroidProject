package com.example.java_design_pattern.builder;

public class Computer {
    private String mainboard;
    private String cpu;
    private String memory;

    public String getMainboard() {
        return mainboard;
    }

    public void setMainboard(String mainboard) {
        this.mainboard = mainboard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "mainboard='" + mainboard + '\'' +
                ", cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
