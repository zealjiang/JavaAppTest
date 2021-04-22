package com.example.lib

fun main(){
    val thread = Thread(MyRunnable())
    thread.start()
    thread.join()
    println("finish")
}

class MyRunnable :Runnable{
    override fun run() {
        println("myThread run start......")
        Thread.sleep(5000)
        println("myThread run end")
    }
}