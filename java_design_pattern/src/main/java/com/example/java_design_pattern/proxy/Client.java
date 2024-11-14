package com.example.java_design_pattern.proxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        // 静态代理
        Producer xiaomi = new XiaomiProducer();
//        ProxyProducer proxyProducer = new ProxyProducer(xiaomi);
//        proxyProducer.createCpu();
//        proxyProducer.createCamera();
//        proxyProducer.assemble();

        // 动态代理
        Producer dynamicProxy = (Producer) Proxy.newProxyInstance(xiaomi.getClass().getClassLoader(),
                new Class[]{Producer.class},
                new DynamicProxyProducer(xiaomi));
        dynamicProxy.createCpu();
        dynamicProxy.createCamera();
        dynamicProxy.assemble();
    }
}
