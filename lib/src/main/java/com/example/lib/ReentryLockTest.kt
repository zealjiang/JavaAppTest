package com.example.lib

import java.lang.Thread.sleep
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

object ReentryLockTest {

    val lock: Lock = ReentrantLock(true)

    @JvmStatic
    fun main(args: Array<String>) {
/*        Thread({ test2() }, "线程A").start()
        Thread({ test2() }, "线程B").start()
        Thread({ test2() }, "线程C").start()
        Thread({ test2() }, "线程D").start()
        Thread({ test2() }, "线程E").start()*/

        testReent()
    }

    fun testReent(){
        val lock = ReentrantLock()
        val executorService: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
        executorService.execute { printTest1()}
    }

    private fun printTest1() {
        lock.lock()
        try {
            println("printTest1")
            printTest2()
        } finally {
            lock.unlock()
        }
    }

    fun printTest2(){
        lock.lock()
        try {
            println("printTest2")
        }finally {
            lock.unlock()
        }
    }



    fun test(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().name + "获取了锁")
            sleep(200)
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            System.out.println(Thread.currentThread().name + "秋放了锁")
            lock.unlock()
        }
    }

    fun test2(){
        for (i in 0..1) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().name + "获取了锁")
                sleep(200)
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                System.out.println(Thread.currentThread().name + "秋放了锁")
                lock.unlock()
            }
        }

    }
}