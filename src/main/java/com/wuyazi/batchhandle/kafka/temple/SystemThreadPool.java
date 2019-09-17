package com.wuyazi.batchhandle.kafka.temple;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 系统线程池
* @ClassName: SystemThreadPool
* @date 2019年2月18日上午9:10:07
* @version V1.0.0
* @since 1.8
*/
@Component
public class SystemThreadPool {

	/** 线程池保持ALIVE状态线程数 */
	public static final int                 CORE_POOL_SIZE      = 12;

	/** 线程池最大线程数 */
	public static final int                 MAX_POOL_SIZE       = 40;

	/** 空闲线程回收时间 */
	public static final int                 KEEP_ALIVE_TIME     = 100;

	/** 线程池等待队列 */
	public static final int                 BLOCKING_QUEUE_SIZE = 10000;

	private static ThreadPoolExecutor executor = null;

	public static ThreadPoolExecutor getThread() {

		if (null == executor) {

			synchronized (SystemThreadPool.class) {
				if (null == executor) {
					BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(BLOCKING_QUEUE_SIZE);
					ThreadFactory threadFactory = new NameTreadFactory();
					executor = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE_TIME, TimeUnit.SECONDS,workQueue,threadFactory);
					// 预启动所有核心线程
					executor.prestartAllCoreThreads();
				}
			}
		}
		return executor;
	}

}
