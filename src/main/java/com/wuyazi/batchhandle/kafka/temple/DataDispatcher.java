package com.wuyazi.batchhandle.kafka.temple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: DataDispatcher
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/9/14 23:57
 * @Version: 1.0
 */
@Component
public class DataDispatcher {

    private int corePoolSize = 1;
    private int maximumPoolSize = 1;
    private long keepAliveTime = 100;

    /**
     * 日志服务
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 接收数据队列
     */
    @Autowired
    private MusicInfoQueue musicInfoQueue;

    @Autowired
    private MusicInfoHandleQueue musicInfoHandleQueue;

    @Autowired
    private BussinessRun bussinessRun;


    @PostConstruct
    public void init() {

        ThreadPoolExecutor executor = SystemThreadPool.getThread();
        executor.execute(new MusicInfoDateRunnable());

    }


    class MusicInfoDateRunnable implements Runnable{
        @Override
        public void run() {

            if (!Thread.interrupted()) {
                try {
                    while (true) {
                        if (musicInfoQueue.getMusicInfoQueue() == null) {
                            break;
                        }

                        int count = musicInfoQueue.getMusicInfoQueue().size();

                        if(count >= 1000){

                            //消费接收数据,一次消费1000
                            List<MusicInfoDto> musicInfoDtos = new ArrayList<>();
                            musicInfoQueue.getMusicInfoQueue().drainTo(musicInfoDtos,1000);

                            //logger.info("接收数据数据 ------> {}", musicInfoDate);

                            try {

                                musicInfoHandleQueue.getMusicInfoListQueue().put(musicInfoDtos);

                            } catch (Exception e) {
                                logger.error("接收数据数据失败------> {}", musicInfoDtos, e);
                            }

                            musicInfoDtos = null;
                            logger.info("观察队列中需要消费的数据条数 ------> {}", musicInfoQueue.getMusicInfoQueue().size());
                        }


                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
