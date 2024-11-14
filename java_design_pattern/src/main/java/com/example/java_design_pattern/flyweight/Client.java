package com.example.java_design_pattern.flyweight;

public class Client {
    public static void main(String[] args) {
        Goods goods1 = GoodsFactory.get("1.0");
        goods1.show();
        Goods goods2 = GoodsFactory.get("1.0");
        goods2.show();
        Goods goods3 = GoodsFactory.get("2.0");
        goods3.show();
    }
}
