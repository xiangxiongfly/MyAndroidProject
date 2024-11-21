package com.example.java_design_pattern.visitor;

public class Monitor implements ComputerPart {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
