package com.example.java_design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyProducer implements InvocationHandler {
    private Producer realObj;

    public DynamicProxyProducer(Producer realObj) {
        this.realObj = realObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(realObj, args);
    }
}
