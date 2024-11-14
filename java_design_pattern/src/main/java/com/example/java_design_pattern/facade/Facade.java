package com.example.java_design_pattern.facade;

public class Facade {
    private Database db;
    private Network network;

    public Facade() {
        db=new Database();
        network=new Network();
    }

    public void sendData(){
        db.connect();
        db.getData();
        network.request();
    }

    public void sendAndCache(){
        network.receiveData();
        db.connect();
        db.saveData();
    }
}
