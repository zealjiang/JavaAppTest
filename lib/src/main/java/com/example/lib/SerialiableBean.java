package com.example.lib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化过程的执行顺序：writeReplace->writeObject；
 * 反序列化过程的执行顺序：readObject->readResolve
 * 通过上面四个方法，可以实现Serializable的自定义序列化。
 */
public class SerialiableBean implements Serializable {

    public String mShopName;
    public int mShopId;
    public String mShopPhone;

    /**
     * 序列化时执行 执行顺序早于writeObject 可以在此方法中做一些替换
     */
/*    private Object writeReplace() {
        System.out.println("-----writeReplace() start-----");
        SerialiableBean shop = new SerialiableBean();
        shop.mShopName = "物美超市";//将mShopName替换
        shop.mShopId = mShopId;
        shop.mShopPhone = mShopPhone;
        return shop;
    }*/

    /**
     * 序列化时执行 通过defaultWriteObject将非transient字段序列化 也可以自定义序列化字段
     */
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        System.out.println("-----writeObject() start-----");
        outputStream.defaultWriteObject();
        outputStream.writeObject(mShopName);
    }

    /**
     * 反序列化时执行 通过defaultReadObject将非transient字段反序列化 也可以将自定义字段反序列化
     */
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        System.out.println("-----readObject() start-----");
        inputStream.defaultReadObject();
        mShopName = (String) inputStream.readObject();
    }

    /**
     * 反序列化时执行，执行顺序在readObject之后 可以在此方法中重新生成一个新对象
     */
/*    private Object readResolve() {
        System.out.println("-----readResolve() start-----");
        SerialiableBean shop = new SerialiableBean();
        shop.mShopName = mShopName;
        shop.mShopId = mShopId;
        shop.mShopPhone = "12345678";//将mShopPhone替换
        return shop;
    }*/

    @Override
    public String toString() {
        return "Serializable: mShopName is " + mShopName
                + ",mShopId is " + mShopId
                + ",mShopPhone is " + mShopPhone;
    }
}
