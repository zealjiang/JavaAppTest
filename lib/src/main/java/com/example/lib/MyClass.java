package com.example.lib;

import com.example.lib.lang.StringsXmlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class MyClass {

    public static void main(String[] args) {
        System.out.println("main->");

        String a = "& পৌঁছাতে # 160 পাল্টানো হয়েছে;";
        //a.replace("&\\S# 160\\S;","&#160;");
        //new StringsXmlUtil().parse();

        if ((0.1f == 0) ^ (0.2f == 0)) {
            System.out.println("aaa");
        }

        int b=2;
        System.out.println("b 非的结果是："+(~b));

        int bit1 = 128;
        System.out.println("bit1："+(bit1 >> 7));

        System.out.println("2 << 3："+(2 << 3));

        int al = 3 << 2;
        System.out.println("3 << 2："+al);

        int COUNT_BITS = 29;
        int RUNNING    = -1 << COUNT_BITS;
        int SHUTDOWN   =  0 << COUNT_BITS;
        int STOP       =  1 << COUNT_BITS;
        int TIDYING    =  2 << COUNT_BITS;
        int TERMINATED =  3 << COUNT_BITS;
        System.out.println("RUNNING："+RUNNING);
        System.out.println("SHUTDOWN："+SHUTDOWN);
        System.out.println("STOP："+STOP);
        System.out.println("TIDYING："+TIDYING);
        System.out.println("TERMINATED："+TERMINATED);

        int core = 3,corePoolSize = 5,maximumPoolSize = 10;
        new FinalClassT();
    }

    public static int findMin01(int n){
        if(n == 0)return 0;
        int i = 1;
        while (true){
            int num = Integer.valueOf(Integer.toBinaryString(i));
            if(num % n == 0){
                return num;
            }
            i++;
            if(num > Integer.MAX_VALUE >> 1)return -1;
        }
    }
}