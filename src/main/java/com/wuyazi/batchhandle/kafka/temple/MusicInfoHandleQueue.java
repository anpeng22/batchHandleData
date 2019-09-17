package com.wuyazi.batchhandle.kafka.temple;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: MusicInfoQueue
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/9/14 23:39
 * @Version: 1.0
 */
@Component
public class MusicInfoHandleQueue {

    /**
     * 记录队列
     */
    private BlockingQueue<List<MusicInfoDto>> musicInfoListQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<List<MusicInfoDto>> getMusicInfoListQueue() {
        return musicInfoListQueue;
    }

    public void setMusicInfoListQueue(BlockingQueue<List<MusicInfoDto>> musicInfoListQueue) {
        this.musicInfoListQueue = musicInfoListQueue;
    }

}
