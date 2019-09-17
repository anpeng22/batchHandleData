package com.wuyazi.batchhandle.kafka.temple;

/**
 * @ClassName: Test1001
 * @Description: TODO
 * @Auther: YCKJ1374
 * @Date: 2019/9/16 19:19
 * @Version: 1.0
 */
public class Test1001 implements Runnable {

    @Override
    public void run() {

        System.out.println("xiancheng-----"+Thread.currentThread().getName());
    }
}
