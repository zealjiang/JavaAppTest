package com.example.lib;

public class StopBoolean {

    // 确保变化对其它线程可见（主要是主线程要可见）
    public static volatile boolean done = false;

    public void run() {
        while (!done) {
            System.out.println("StopBoolean running");
/*            try {
                sleep(200);
            } catch (InterruptedException e) {
                return;
            }*/
        }
        System.out.println("StopBoolean finished");
    }

    public void shutDown() {
        done = true;
    }
    public void closeDown() {
        done = false;
    }

    Thread test = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!done) {
                System.out.println("StopBoolean running");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println("StopBoolean finished");
        }
    });


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            StopBoolean t1 = new StopBoolean();
            t1.test.start();
        }

        Thread.sleep(1000);
        System.out.println("shutDown before");
        done = true;
        System.out.println("shutDown after");

    }
}