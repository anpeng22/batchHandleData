package com.wuyazi.batchhandle.kafka.temple;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: NameTreadFactory
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/8/14 15:54
 * @Version: 1.0
 */
public class NameTreadFactory implements ThreadFactory {

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
        System.out.println(t.getName() + " has been created");
        return t;
    }
}
