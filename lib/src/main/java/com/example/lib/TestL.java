package com.example.lib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestL {

    public static void main(String[] args) {
        TestL testL = new TestL();
        //testL.te();
        //testL.interruptTest();

        testL.testThreadLocalSave();
    }

    void joinTest(){
        Thread thread1 = new Thread("t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        join(1000);
                    } catch (InterruptedException e) {
                        System.out.println("t1 InterruptedException "+e.getMessage());
                        e.printStackTrace();
                        break;
                    }
                    System.out.println("t1 running "+n);
                    n++;
                }
            }
        };

        thread1.start();
        try {
            thread1.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
    }

    int n = 0;
    void interruptTest(){
        Thread thread1 = new Thread("t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        System.out.println("t1 InterruptedException "+e.getMessage());
                        e.printStackTrace();
                        break;
                    }
                    System.out.println("t1 running "+n);
                    n++;
                }
            }
        };

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
    }

    void te(){
        new Thread(() -> test(),"线程A").start();
        new Thread(() -> test2(),"线程B").start();
    }
    int i;
     void test(){
        synchronized (TestL.this) {
            i++;
            if(i>3)return;
            System.out.println("test()  ***");
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test  "+Thread.currentThread().getName());
                test2();
            }
        }
    }

     void test2(){
        synchronized (TestL.this) {
            i++;
            if(i>3)return;
            System.out.println("test2()  ---+++++---");
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test2  ====="+Thread.currentThread().getName());
                test();
            }
        }
    }

    private void testThreadLocalSave(){
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("a");
        System.out.println(threadLocal.get());

        threadLocal = new ThreadLocal<>();
        threadLocal.set("b");
        System.out.println(threadLocal.get());

        threadLocal = new ThreadLocal<>();
        threadLocal.set("c");
        System.out.println(threadLocal.get());

        threadLocal = new ThreadLocal<>();
        threadLocal.set("a");
        System.out.println(threadLocal.get());
        threadLocal.set("d");
        System.out.println(threadLocal.get());

        getLooperThreadLocal(threadLocal,Thread.currentThread());
    }

    private void getLooperThreadLocal(ThreadLocal<String> threadLocal,Thread t){
        try {
            Method methodGetMap = threadLocal.getClass().getDeclaredMethod("getMap",Thread.class);
            methodGetMap.setAccessible(true);
            methodGetMap.invoke(threadLocal,t);

            Class classThreadLocalMap = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
            Field fieldSize = classThreadLocalMap.getDeclaredField("size");
            fieldSize.setAccessible(true);
            int size = (int) fieldSize.get(methodGetMap.invoke(threadLocal,t));
/*            Field fieldEntrys = classThreadLocalMap.getDeclaredField("table");
            fieldEntrys.setAccessible(true);
            int size = ((Object[])fieldEntrys.get(methodGetMap.invoke(threadLocal,t))).length;*/
            System.out.println("size: "+size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
