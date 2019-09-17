package com.wuyazi.batchhandle.kafka.temple;

import lombok.Data;

/**
 * @ClassName: MusicInfoDto
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/9/14 23:40
 * @Version: 1.0
 */
@Data
public class MusicInfoDto {

    /**
     * 主键id
     */
    private String id;

    /**
     * 歌手名
     */
    private String singerName;

    /**
     * 歌曲名
     */
    private String musicName;

    /**
     * 歌曲大小
     */
    private String musicSize;

    /**
     * 专辑名称
     */
    private String albumName;

    /**
     * 发行时间
     */
    private String publishTime;

    /**
     * 发行地点
     */
    private String publishPlace;

    /**
     * 歌词
     */
    private String lyric;


    /**
     * 歌曲类型1中文2英文
     */
    private String musicType;

    /**
     * 创建时间
     */
    private Long createTime;
}
