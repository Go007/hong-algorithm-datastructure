package com.hong.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author wanghong
 * @date 2020/03/21 18:01
 * 反射破坏单例模式
 **/
public class Main0 {
    public static void main(String[] args) {
        Singleton0 s0 = Singleton0.getInstance();
        Singleton0 s1 = null;
        try {
            // 1.获取Class
            Class<Singleton0> clazz = Singleton0.class;
            // 2. 获取默认构造函数
            Constructor<Singleton0> con = clazz.getDeclaredConstructor();
            // 3. 设置默认构造函数可访问
            con.setAccessible(true);
            // 4. 暴力反射创建对象
            s1 = con.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }

        // 5. 比较hashCode
        System.out.println(s0.hashCode());
        System.out.println(s1.hashCode());
    }
}
