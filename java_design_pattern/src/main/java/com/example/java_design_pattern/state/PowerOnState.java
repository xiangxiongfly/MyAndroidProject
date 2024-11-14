package com.example.java_design_pattern.state;

public class PowerOnState implements PowerState {

    @Override
    public void prevSong() {
        System.out.println("上一首");
    }

    @Override
    public void nextSong() {
        System.out.println("下一首");
    }
}
