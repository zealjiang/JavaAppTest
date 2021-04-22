package com.example.lib.proxy;

import java.util.HashMap;

public class FactoryCarImpl implements FactoryCar{
    @Override
    public void createCar() {
        System.out.println("create a dazhong car");
    }
}
