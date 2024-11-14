package com.example.java_design_pattern.state;

public class PowerOffState implements PowerState {

    @Override
    public void prevSong() {
        System.out.println("请先开机");
    }

    @Override
    public void nextSong() {
        System.out.println("请先开机");
    }
}
