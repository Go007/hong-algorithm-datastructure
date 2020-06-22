package com.hong.other;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/6/22 15:04
 * @Version V1.0
 *
 * Class.forName() 和 ClassLoader 加载类区别
 **/
public class TestClassLoad {

    public static void main(String[] args) {
        classForName();
        System.out.println("==========================");
        classLoader();
    }

    public static void classForName(){
        try {
            Class.forName("com.hong.other.ClassForName");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void classLoader(){
        try {
            ClassLoader.getSystemClassLoader().loadClass("com.hong.other.ClassForName");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
