package com.example.java_design_pattern.chainofresponsibility;

public abstract class Handler {
    private Handler nextHandler;
    private int level;

    public Handler(int level) {
        this.level = level;
    }

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    public final void handleRequest(Request request) {
        if (level == request.getLevel()) {
            this.response(request);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.handleRequest(request);
            } else {
                System.out.println("没有处理器");
            }
        }
    }

    public abstract void response(Request request);
}
