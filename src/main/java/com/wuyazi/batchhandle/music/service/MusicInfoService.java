package com.wuyazi.batchhandle.music.service;


import com.wuyazi.batchhandle.kafka.temple.MusicInfoDto;
import com.wuyazi.batchhandle.music.domain.MusicInfo;

import java.util.List;

/**
 * Title: MusicInfoService
 * Description:音乐类
 * @author anzhiming
 * @date 2019年08月12日
 */
public interface MusicInfoService {

    /**
     * 查询信息
     * @param musicInfo
     * @return
     */
    List<MusicInfo> getMusicInfo(MusicInfo musicInfo);

    /**
     * 处理接收数据
     * @param musicInfoDtos
     * @return
     */
    void handleReceveData(List<MusicInfoDto> musicInfoDtos) throws Exception;
}
