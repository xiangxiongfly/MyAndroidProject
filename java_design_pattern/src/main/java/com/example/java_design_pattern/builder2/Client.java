package com.example.java_design_pattern.builder2;

public class Client {
    public static void main(String[] args) {
        Computer computer = Computer.build()
                .buildMainboard("技嘉")
                .buildCpu("AMD")
                .buildMemory("金士顿")
                .create();
        System.out.println(computer);
    }
}
