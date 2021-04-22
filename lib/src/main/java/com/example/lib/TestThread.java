package com.example.lib;

import java.io.IOException;

public class TestThread extends Thread {
    // 永真循环线程
    public void run() {
        for (int i = 0;; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("run 。。。。");
            } catch (InterruptedException ex) {
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        TestThread test = new TestThread();
        // 调试时可以设置为false，那么这个程序是个死循环，没有退出条件。设置为true，即可主线程结束，test线程也结束。
        test.setDaemon(true);
        test.start();
        System.out.println("isDaemon = " + test.isDaemon());
        try {
            //main线程结束，守护线程自动结束
            Thread.sleep(3000);
        } catch (Exception ex) {
        }
        System.out.println("main sleep over " + Thread.currentThread().isDaemon());
    }
}