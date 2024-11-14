package com.example.java_design_pattern.strategy;

public class BusStrategy implements Strategy {
    @Override
    public int calculatePrice(int km) {
        int price = 1;
        //超出10km的距离
        int extraTotal = km - 10;
        int extraParts = extraTotal / 5;
        extraParts = extraTotal % 5 > 0 ? ++extraParts : extraParts;
        price += extraParts * 1;
        return price;
    }
}
