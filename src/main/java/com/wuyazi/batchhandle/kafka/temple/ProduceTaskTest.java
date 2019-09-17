package com.wuyazi.batchhandle.kafka.temple;

import com.wuyazi.batchhandle.common.util.UUIDUtils;
import com.wuyazi.batchhandle.music.domain.MusicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MonitorUpdateTask
 * @Description: 布控任务状态同步定时器，每个3分钟执行一次
 * @Auther: YCKJ1374
 * @Date: 2019/3/27 20:01
 * @Version: 1.0
 */
@Component
@EnableScheduling
public class ProduceTaskTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static AtomicInteger idato = new AtomicInteger(1);

    @Autowired
    private MusicInfoProducer musicInfoProducer;

    /**
     * 6分钟发送一次 每次
     */
    @Scheduled(cron = "0/1 * * * * ?")     //0 53 15 * * ?   0/1 * * * * ? 一秒钟  0 0/1 * * * ? 一分钟
    public void produceHandle()  {

        int current = idato.getAndIncrement();

        if(current % 100 == 0){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{

            for (int i = 0; i < 4000; i++) {
                //调用消息发送类中的消息发送方法
                MusicInfo musicInfo = new MusicInfo();
                musicInfo.setId(UUIDUtils.get32UUID());
                musicInfo.setSingerName("singername" + i);
                musicInfo.setMusicName("musicname" + i);
                musicInfo.setMusicSize(i+"M");
                musicInfo.setAlbumName("专辑名");
                musicInfo.setPublishTime(System.currentTimeMillis() +"");
                musicInfo.setPublishPlace("publishplace" + i);
                musicInfo.setLyric("欲将沉醉换悲凉，清歌莫断肠。这混乱的尘世，究竟充斥了多少绝望和悲伤。");
                musicInfo.setMusicType("1");
                musicInfo.setCreateTime(System.currentTimeMillis());

                musicInfoProducer.sendMusicInfo(musicInfo);
            }
        }
    }

}
