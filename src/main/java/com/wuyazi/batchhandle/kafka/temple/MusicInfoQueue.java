package com.wuyazi.batchhandle.kafka.temple;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: MusicInfoQueue
 * @Description: TODO
 * @Auther: YCKJ1374
 * @Date: 2019/9/14 23:39
 * @Version: 1.0
 */
@Component
public class MusicInfoQueue {

    /**
     * 记录队列
     */
    private BlockingQueue<MusicInfoDto> musicInfoQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<MusicInfoDto> getMusicInfoQueue() {
        return musicInfoQueue;
    }

    public void setMusicInfoQueue(BlockingQueue<MusicInfoDto> musicInfoQueue) {
        this.musicInfoQueue = musicInfoQueue;
    }

}
