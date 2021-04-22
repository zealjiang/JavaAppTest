package com.example.javaapptest;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class TestClass {

    private static TestClass instance;
    public static TestClass getInstance(Context context){
        if(instance == null){
            instance = new TestClass(context);
        }
        return instance;
    }

    interface TestListener{
        void onSomeThing();
    }

    private Context context;
    private final List<TestListener> listenerList = new ArrayList<>();

    public TestClass(Context context){
        this.context = context;
    }

    public void registerListener(TestListener listener){
        this.listenerList.add(listener);
    }

    public void unRegisterListener(TestListener listener){
        this.listenerList.remove(listener);
    }

    public void doSomeThing(){
        new Thread("do-some-thing"){
            @Override
            public void run() {
                doSomeThingInternal();
                for (TestListener listener : listenerList) {
                    listener.onSomeThing();
                }
            }
        }.start();
    }

    private void doSomeThingInternal(){}
}
