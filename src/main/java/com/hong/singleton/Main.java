package com.hong.singleton;

import java.io.*;

/**
 * @author wanghong
 * @date 2020/03/21 18:31
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        Singleton s0 = Singleton.getInstance();
        String path = "tempFile";
        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(s0);

        File file = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        // 反序列化
        Singleton s1 = (Singleton) ois.readObject();
        System.out.println("正常构造：" + s0.hashCode());
        System.out.println("反序列化：" + s1.hashCode());
    }
}
