package com.example.java_design_pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class GoodsFactory {
    private static final Map<String, Goods> pool = new HashMap<>();

    public static Goods get(String version) {
        Goods goods = pool.get(version);
        if (goods == null) {
            System.out.println("创建新对象");
            goods = new Goods(System.nanoTime(), version);
            pool.put(version, goods);
        } else {
            System.out.println("使用缓存");
        }
        return goods;
    }
}
