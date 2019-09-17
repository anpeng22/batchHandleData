package com.wuyazi.batchhandle.kafka.temple;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: Test2
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/9/17 09:54
 * @Version: 1.0
 */
public class Test2 {

    private static AtomicInteger idato = new AtomicInteger(1);

    public static void main(String[] args) throws Exception {

//        for (int i = 0; i < 20; i++) {
//            System.out.println("get====" + idato.get());
//            System.out.println("get====" + idato.getAndIncrement());
//        }

        System.out.println(20 % 100);
        System.out.println(5 % 100);
        System.out.println(100 % 100);
        System.out.println(200 % 100);
        System.out.println(517 % 100);
    }
}
