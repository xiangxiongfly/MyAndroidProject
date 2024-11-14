package com.example.java_design_pattern.mediator;

import java.util.ArrayList;

import static com.example.java_design_pattern.mediator.Const.TYPE_BUY;
import static com.example.java_design_pattern.mediator.Const.TYPE_SELL;

public class HouseMediator extends Mediator {

    private SellHouse sellHouse;
    private final ArrayList<BuyHouse> buyHouseList = new ArrayList<>();

    public void setSellHouse(SellHouse sellHouse) {
        this.sellHouse = sellHouse;
    }

    public void addBuyHouse(BuyHouse buyHouseColleague) {
        buyHouseList.add(buyHouseColleague);
    }

    @Override
    public void notify(String message, int type) {
        if (type == TYPE_SELL) {
            for (BuyHouse i : buyHouseList) {
                i.receiveMessage(message);
            }
        } else if (type == TYPE_BUY) {
            sellHouse.receiveMessage(message);
        }
    }
}
