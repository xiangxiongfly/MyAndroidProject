package com.example.java_design_pattern.proxy;

public class XiaomiProducer implements Producer {
    @Override
    public void createCpu() {
        System.out.println("生产CPU");
    }

    @Override
    public void createCamera() {
        System.out.println("生产摄像头");
    }

    @Override
    public void assemble() {
        System.out.println("组装");
    }
}
