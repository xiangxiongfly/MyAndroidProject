package com.example.java_design_pattern.builder2;

public class Computer {
    private String mainboard;
    private String cpu;
    private String memory;

    private Computer() {
    }

    @Override
    public String toString() {
        return "Computer{" +
                "mainboard='" + mainboard + '\'' +
                ", cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private Computer computer;

        private Builder() {
            computer = new Computer();
        }

        public Builder buildMainboard(String mainboard) {
            computer.mainboard = mainboard;
            return this;
        }

        public Builder buildCpu(String cpu) {
            computer.cpu = cpu;
            return this;
        }

        public Builder buildMemory(String memory) {
            computer.memory = memory;
            return this;
        }

        public Computer create() {
            return computer;
        }
    }
}
