package com.wuyazi.batchhandle.kafka.temple;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName: MusicInfoConsumer
 * @Description: TODO
 * @Auther: YCKJ1374
 * @Date: 2019/9/14 23:10
 * @Version: 1.0
 */
@Component
public class MusicInfoConsumer {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MusicInfoQueue musicInfoQueue;

    @KafkaListener(topics = {"test2"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

//            log.info("----------------- record =" + record);
//            log.info("------------------ message =" + message);

            String content = null;
            try {

                content = new String(message.toString().getBytes(), "UTF-8");

//                log.info("接收待数据,主题:{},数据:{}", "test2", content);
                //抓换为报警记录
                MusicInfoDto info = JSONObject.parseObject(content, MusicInfoDto.class);

                if (null == info) {
                    return;
                }
                //加入到报警流水数据队列
                musicInfoQueue.getMusicInfoQueue().put(info);

            } catch (Exception e) {
                log.error("接收待发送的数据,主题:{},数据:{}异常,信息:{}", "test2", content, e);
                return;
            }

        }

    }
}
