package com.wuyazi.batchhandle.index.controller;

import com.wuyazi.batchhandle.music.domain.MusicInfo;
import com.wuyazi.batchhandle.music.service.MusicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Title: IndexController
 * Description:测试服务类
 * @author an
 * @date 2019年08月12日
 */
@Controller
public class IndexController {

    @Autowired
    private MusicInfoService musicInfoService;

    /**
     * 日志服务
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 查询数据
     * @param musicInfo
     * @return
     */
    @RequestMapping("/music")
    @ResponseBody
    public List<MusicInfo> getMusicInfo(MusicInfo musicInfo) {
        List<MusicInfo> musicInfoList = musicInfoService.getMusicInfo(null);
        return musicInfoList;
    }


}
