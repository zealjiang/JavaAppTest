package com.example.lib;

import java.util.logging.Handler;

public class FanType {

    Runnable run;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };

        RA ra = new RA();
        ra.firstTask = runnable;

        Runnable task = ra.firstTask;
        ra.firstTask = null;

        System.out.println("task : "+task);
        if(task != null){
            task.run();
        }

    }


    public static class RA{
        public Runnable firstTask;
    }
}
