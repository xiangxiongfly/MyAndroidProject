package com.example.java_design_pattern.state;

public class MusicContext {
    private PowerState offState;
    private PowerState onState;
    private PowerState currentState;

    public MusicContext() {
        offState = new PowerOffState();
        onState = new PowerOnState();
    }

    public void setMusicState(PowerState musicState) {
        this.currentState = musicState;
    }

    public void powerOn() {
        setMusicState(onState);
        System.out.println("开机");
    }

    public void powerOff() {
        setMusicState(offState);
        System.out.println("关机");
    }

    public void nextSong() {
        currentState.nextSong();
    }

    public void prevSong() {
        currentState.prevSong();
    }
}
