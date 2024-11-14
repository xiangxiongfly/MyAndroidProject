package com.example.java_design_pattern.proxy;

public class ProxyProducer implements Producer {
    private Producer producer = null;

    public ProxyProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void createCpu() {
        producer.createCpu();
    }

    @Override
    public void createCamera() {
        producer.createCamera();
    }

    @Override
    public void assemble() {
        producer.assemble();
    }
}
