package com.example.javaapptest

class T1Class {

    companion object {
        val myClassField1: Int = 1
        fun companionFun() {
            println("this is 2st companion function.")
        }

        @JvmField
        val name: String = "english"

        @JvmStatic
        fun method(){
            println("method jvmStatic")
        }
    }

    fun t(){
        SingleTonObj.name
        SingleTonObj.add()
    }
}