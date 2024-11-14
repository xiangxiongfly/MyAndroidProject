package com.example.java_design_pattern.mediator;

public class Client {
    public static void main(String[] args) {
        HouseMediator houseMediator = new HouseMediator();
        SellHouse sell = new SellHouse("小明", houseMediator);
        BuyHouse buy1 = new BuyHouse("买家A", houseMediator);
        BuyHouse buy2 = new BuyHouse("买家B", houseMediator);
        houseMediator.setSellHouse(sell);
        houseMediator.addBuyHouse(buy1);
        houseMediator.addBuyHouse(buy2);
        sell.sendMessage("我要卖房");
        System.out.println("------------");
        buy1.sendMessage("我要看房");
    }
}
