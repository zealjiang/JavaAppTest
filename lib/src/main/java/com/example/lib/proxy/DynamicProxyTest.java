package com.example.lib.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Vector;

public class DynamicProxyTest {

    public static void main(String[] args) {
        ProxyFactoryCar proxy = new ProxyFactoryCar();
        FactoryCar factoryCar = (FactoryCar) proxy.newProxyInstance(new FactoryCarImpl());
        factoryCar.createCar();

        DynamicProxyTest test = new DynamicProxyTest();
        test.removeDuplicateChars("AHIOUHAOFNIA发货价啦AFG");
    }

    

    //输入的字符串 String  s = "AHIOUHAOFNIA发货价啦AFG"；
    //经过处理之后结果应该是："AHIOUFN发货价啦"

    private String removeDuplicateChars(String s) {

        LinkedHashSet<Character> linkedHashSet = new LinkedHashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            linkedHashSet.add(chars[i]);
        }

        StringBuilder stringBuilder = new StringBuilder();
        Iterator<Character> iterator = linkedHashSet.iterator();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        System.out.println(stringBuilder.toString());


/*        ArrayList<Character> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(!list.contains(chars[i])){
                list.add(chars[i]);
            }else{
                continue;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
        }
        System.out.println(stringBuilder.toString());*/

        return stringBuilder.toString();
    }

}
