package com.example.java_design_pattern.strategy;

public class PriceCalculator {
    public static final int TYPE_BUS = 1;
    public static final int TYPE_SUBWAY = 2;

    /**
     * 公交车计价
     */
    private int busPrice(int km) {
        int price = 1;
        //超出10km的距离
        int extraTotal = km - 10;
        int extraParts = extraTotal / 5;
        extraParts = extraTotal % 5 > 0 ? ++extraParts : extraParts;
        price += extraParts * 1;
        return price;
    }


    /**
     * 地铁计价
     */
    private int subwayPrice(int km) {
        if (km <= 6) {
            return 3;
        } else if (km > 6 && km <= 12) {
            return 4;
        } else if (km > 12 && km <= 22) {
            return 5;
        } else if (km > 22 && km <= 32) {
            return 6;
        } else {
            return 7;
        }
    }

    public int calculatorPrice(int type, int km) {
        switch (type) {
            case TYPE_BUS:
                return busPrice(km);
            case TYPE_SUBWAY:
                return subwayPrice(km);
            default:
                return 0;
        }
    }
}
