package com.example.java_design_pattern.visitor;

public class ConcreteVisitor implements Visitor {
    @Override
    public void visit(Computer computer) {
        System.out.println("访问Computer");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("访问Mouse");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("访问Keyboard");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("访问Monitor");
    }
}
