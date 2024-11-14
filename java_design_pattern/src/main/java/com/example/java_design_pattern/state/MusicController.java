package com.example.java_design_pattern.state;

public class MusicController {
    private static final int POWER_ON = 1;
    private static final int POWER_OFF = 2;
    private int state = POWER_OFF;

    public void powerOn() {
        state = POWER_ON;
        System.out.println("开机");
    }

    public void powerOff() {
        state = POWER_OFF;
        System.out.println("关机");
    }

    public void prevSong() {
        if (state == POWER_ON) {
            System.out.println("上一首");
        }
    }

    public void nextSong() {
        if (state == POWER_ON) {
            System.out.println("下一首");
        }
    }
}
