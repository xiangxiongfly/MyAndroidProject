package com.example.java_design_pattern.flyweight;

public class Goods implements IGoods {
    private long id;
    private String version;

    public Goods(long id, String version) {
        this.id = id;
        this.version = version;
    }

    @Override
    public void show() {
        System.out.println(id + "-" + version);
    }
}
