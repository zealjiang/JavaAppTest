package com.example.lib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerialiableBean {

    public static void main(String[] args) {

        //------------------Serializable------------------
        SerialiableBean shop = new SerialiableBean();
        shop.mShopName = "便利蜂";
        shop.mShopId = 2020;
        shop.mShopPhone = "18888888888";

        saveObject(shop); //序列化
        readObject();//反序列化
    }


    //序列化
    private static void saveObject(SerialiableBean shop) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("shop.obj"));
            outputStream.writeObject(shop);
            System.out.println("write-hashCode: " + shop.hashCode());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void readObject() {
        //反序列化
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("shop.obj"));
            SerialiableBean shop = (SerialiableBean) inputStream.readObject();
            System.out.println(shop.toString());
            System.out.println("read-hashCode: " + shop.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
