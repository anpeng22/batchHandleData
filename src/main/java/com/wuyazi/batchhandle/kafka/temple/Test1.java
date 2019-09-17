package com.wuyazi.batchhandle.kafka.temple;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Test1
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/9/16 19:15
 * @Version: 1.0
 */
public class Test1 {


    /** 线程池保持ALIVE状态线程数 */
    public static final int                 CORE_POOL_SIZE      = 10;

    /** 线程池最大线程数 */
    public static final int                 MAX_POOL_SIZE       = 40;

    /** 空闲线程回收时间 */
    public static final int                 KEEP_ALIVE_TIME     = 100;

    /** 线程池等待队列 */
    public static final int                 BLOCKING_QUEUE_SIZE = 1000;

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(BLOCKING_QUEUE_SIZE),new NameTreadFactory());

        while(true) {
            Test1001 st = new Test1001();

            System.out.println("队列中数量=============" + executor.getQueue().size());

            if(executor.getQueue().size() >= 1000){
                try {
                    Thread.sleep(3000);
                    executor.execute(st);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            executor.submit(st);
        }

    }

}
