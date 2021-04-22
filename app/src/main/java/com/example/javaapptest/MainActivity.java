package com.example.javaapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.javaapptest.drawable.RoundedCornersDrawable;
import com.util.PermissionUtil;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.setStatusBarColor(Color.RED);

        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改（一种是深色，一种是浅色即白色）
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        //new StringsXmlUtil().parse(this);

        //new KMainActivity().trim();

        //PermissionUtil.checkPermissionGranted(this,"write");
        //testCornerView();

        //testThreadLocalIsSame();


    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(MainActivity.this,"点1我你就发了", Toast.LENGTH_SHORT).show();
        try {
            int i = 0;
            while (true){
                i++;
                Thread.sleep(200);
                if(i > 1000)return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(MainActivity.this,"点我你就发了", Toast.LENGTH_SHORT).show();
    }

    private void testThreadLocalIsSame(){
        Looper looper = Looper.getMainLooper();
        getLooperThreadLocal(looper,"mainLooper");
/*        Looper.prepare();
        Looper looperNormal = Looper.myLooper();
        getLooperThreadLocal(looperNormal,"looperNormal");*/
        /**
         * 上面注释的代码如果运行，会报如下错误：
         *     Caused by: java.lang.RuntimeException: Only one Looper may be created per thread
         *         at android.os.Looper.prepare(Looper.java:117)
         *         at android.os.Looper.prepare(Looper.java:112)
         *         at com.example.javaapptest.MainActivity.testThreadLocalIsSame(MainActivity.java:62)
         *         at com.example.javaapptest.MainActivity.onCreate(MainActivity.java:56)
         *  原因是因为，我们项目的启动的时候就在ActivityThread里创建了MainLooper，在Activity里再调用Looper.prepare()方法时，因为looper已经存在了，所以就报错了
         */
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Looper looperThread = Looper.myLooper();
                        getLooperThreadLocal(looperThread,"looperThread");
                    }
                }
        ).start();
    }

    private void testCornerView(){
        View view = findViewById(R.id.viewCorner);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.a1);
        //RoundedCornersDrawable roundedCornersDrawable = new RoundedCornersDrawable(new BitmapDrawable(getResources(),bitmap));
        RoundedCornersDrawable roundedCornersDrawable = new RoundedCornersDrawable();
        //view.setBackground(new ShapeDrawable(new RoundRectShape(new float[]{20,20,20,20,20,20,20,20},null,null)));
        view.setBackground(roundedCornersDrawable);
        roundedCornersDrawable.setCircle(true,bitmap);
    }

    private void getLooperThreadLocal(Looper looper,String name){
        try {
            Field field = looper.getClass().getDeclaredField("sThreadLocal");
            field.setAccessible(true);
            ThreadLocal<Looper> threadLocal = (ThreadLocal<Looper>) field.get(looper);
            Log.d("mtest","looper threadlocal name: "+name+"  hashCode: "+threadLocal.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
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
    }

/*    private void getLooperThreadLocal(ThreadLocal<String> threadLocal,Thread t){
        try {
            Method methodGetMap = threadLocal.getClass().getDeclaredMethod("getMap",Thread.class);
            ThreadLocalMap threadLocalMap = methodGetMap.invoke(threadLocal,t);
            Class threadLocalMap = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");

            Field field = looper.getClass().getDeclaredField("sThreadLocal");
            field.setAccessible(true);
            ThreadLocal<Looper> threadLocal = (ThreadLocal<Looper>) field.get(looper);
            Log.d("mtest","looper threadlocal name: "+name+"  hashCode: "+threadLocal.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}