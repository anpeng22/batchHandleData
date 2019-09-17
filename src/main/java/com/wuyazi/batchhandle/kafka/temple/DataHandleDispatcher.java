package com.wuyazi.batchhandle.kafka.temple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
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
public class DataHandleDispatcher {

    /**
     * 日志服务
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 接收数据队列
     */

    @Autowired
    private MusicInfoHandleQueue musicInfoHandleQueue;

    @Autowired
    private BussinessRun bussinessRun;


    @PostConstruct
    public void init() {

        ThreadPoolExecutor executor = SystemThreadPool.getThread();

        executor.execute(new MusicInfoDateHandleRunnable());

    }


    class MusicInfoDateHandleRunnable implements Runnable{
        @Override
        public void run() {

            if (!Thread.interrupted()) {
                try {
                    while (true) {
                        if (musicInfoHandleQueue.getMusicInfoListQueue() == null) {
                            break;
                        }

                        List<MusicInfoDto> musicInfoDtos = musicInfoHandleQueue.getMusicInfoListQueue().take();

                        if( !CollectionUtils.isEmpty(musicInfoDtos) ){
                            try {

                                //处理解析入库逻辑
                                bussinessRun.handleData(musicInfoDtos);

                            } catch (Exception e) {
                                logger.error("接收数据数据失败------> {}", musicInfoDtos, e);
                            }

                            musicInfoDtos = null;
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
