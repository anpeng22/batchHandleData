package com.wuyazi.batchhandle.music.service.impl;

import com.alibaba.fastjson.JSON;
import com.wuyazi.batchhandle.kafka.temple.MusicInfoDto;
import com.wuyazi.batchhandle.music.domain.MusicInfo;
import com.wuyazi.batchhandle.music.mapper.MusicInfoMapper;
import com.wuyazi.batchhandle.music.service.MusicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: MusicInfoServiceImpl
 * Description:音乐服务实现类
 * @author anzhiming
 * @date 2019年08月12日
 */
@Service(value = "musicInfoService")
public class MusicInfoServiceImpl implements MusicInfoService {



    /**
     * 日志服务
     */
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private MusicInfoMapper musicInfoMapper;

    @Override
    public List<MusicInfo> getMusicInfo(MusicInfo musicInfo) {
        List<MusicInfo> musicInfos = musicInfoMapper.selectMusicInfos(null);
        return musicInfos;
    }

    /**
     * 处理接收数据
     * @param musicInfoDtos
     * @return
     */
    @Override
    public void handleReceveData(List<MusicInfoDto> musicInfoDtos) throws Exception{

        List<MusicInfo> musicInfos = new ArrayList<>();
        if( !CollectionUtils.isEmpty(musicInfoDtos)){
            try {
                for(MusicInfoDto musicInfoDto : musicInfoDtos){
                    MusicInfo musicInfo = new MusicInfo();
                    BeanUtils.copyProperties(musicInfoDto,musicInfo);
                    musicInfos.add(musicInfo);
                }

                musicInfoMapper.batchInsertMusicInfos(musicInfos);

                logger.info("+++++++++++++++++++++入库数据 = {}", musicInfos);

            }catch (Exception e){
                logger.error("数据入库异常 ------> {}",JSON.toJSONString(e.getMessage()));

            }
        }

    }

}
