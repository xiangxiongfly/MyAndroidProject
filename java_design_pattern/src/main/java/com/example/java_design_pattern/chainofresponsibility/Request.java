package com.example.java_design_pattern.chainofresponsibility;

public class Request {
    private int level = 0;

    public Request(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
