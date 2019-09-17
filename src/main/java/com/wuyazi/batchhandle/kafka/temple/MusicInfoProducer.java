package com.wuyazi.batchhandle.kafka.temple;

import com.alibaba.fastjson.JSON;
import com.wuyazi.batchhandle.music.domain.MusicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: MusicInfoProducer
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/9/9 11:45
 * @Version: 1.0
 */
@Component
public class MusicInfoProducer {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送数据
     * @param musicInfos
     */
    public void sendMusicInfo(MusicInfo musicInfos){


//        log.info("+++++++++++++++++++++  message = {}", musicInfos);

        kafkaTemplate.send("test2", JSON.toJSONString(musicInfos));

//        log.info("+++++++++++++++++++++ 发送结束  = {}", musicInfos);
    }
}
