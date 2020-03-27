package com.hong.util;

import javax.net.ssl.TrustManagerFactory;

public class Main {
    public static void main(String[] args) {
        String algorithm = TrustManagerFactory.getDefaultAlgorithm();
        System.out.println(algorithm);

    }
}
