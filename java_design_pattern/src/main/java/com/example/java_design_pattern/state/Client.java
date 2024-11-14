package com.example.java_design_pattern.state;

public class Client {
    public static void main(String[] args) {
//        MusicController controller = new MusicController();
//        controller.powerOn();
//        controller.nextSong();
//        controller.prevSong();
//        controller.powerOff();
//        controller.nextSong();

        MusicContext context = new MusicContext();
        context.powerOn();
        context.nextSong();
        context.prevSong();
        context.powerOff();
        context.prevSong();
    }
}
