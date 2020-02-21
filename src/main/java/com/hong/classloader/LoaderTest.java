package com.hong.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wanghong
 * @date 2020/02/21 15:23
 *  手动指定class加载
 **/
public class LoaderTest {
    public static void main(String[] args) throws Exception {
        URL classUrl = new URL("file:D:\\");//jvm 类放在位置

        URLClassLoader loader = new URLClassLoader(new URL[]{classUrl});

        while (true) {
            //if (loader == null) break;
            // 创建一个新的类加载器
           // URLClassLoader loader = new URLClassLoader(new URL[]{classUrl});

            // 问题：静态块触发   Object newInstance = clazz.newInstance();第一次使用时触发
            Class clazz = loader.loadClass("HelloService");
            System.out.println("HelloService所使用的类加载器：" + clazz.getClassLoader());

            Object newInstance = clazz.newInstance();
            Object value = clazz.getMethod("test").invoke(newInstance);
            System.out.println("调用getValue获得的返回值为：" + value);

          //  Thread.sleep(3000L); // 1秒执行一次
           // System.out.println();

            //  help gc  -verbose:class
           // newInstance = null;
           // loader = null;

        }

        // System.gc();
    }
}
