package com.example.lib;

import java.util.logging.Handler;

public class ThreadJoinTest {

    Object obj = new Object();
    public static void main(String[] args) {
        ThreadJoinTest test = new ThreadJoinTest();
        test.joinTest();
    }

      void joinTest(){
        Thread thread = new Thread(new myThread());
        thread.start();

          synchronized(obj) {
              try {
                  obj.wait();
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }

        System.out.println("finish");
    }

    class myThread implements Runnable{

        @Override
        public void run() {

            System.out.println("myThread run start......");
            try {
                Thread.sleep(3 * 1000); //等待5s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("myThread run end");

            synchronized (obj) {
                obj.notify();
            }
        }
    }
}
