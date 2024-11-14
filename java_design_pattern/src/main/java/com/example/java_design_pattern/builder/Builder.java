package com.example.java_design_pattern.builder;

public abstract class Builder {
    public abstract void buildMainboard(String mainboard);

    public abstract void buildCpu(String cpu);

    public abstract void buildMemory(String memory);

    public abstract Computer create();
}
