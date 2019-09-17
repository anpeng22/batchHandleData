package com.wuyazi.batchhandle.music.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Title: MusicInfo
 * Description:音乐类
 * @author anzhiming
 * @date 2019年09月09日
 */
@Data
@Table(name = "tb_musicinfo")
public class MusicInfo {

    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 歌手名
     */
    @Column(name = "singer_name")
    private String singerName;

    /**
     * 歌曲名
     */
    @Column(name = "music_name")
    private String musicName;

    /**
     * 歌曲大小
     */
    @Column(name = "music_size")
    private String musicSize;

    /**
     * 专辑名称
     */
    @Column(name = "album_name")
    private String albumName;

    /**
     * 发行时间
     */
    @Column(name = "publish_time")
    private String publishTime;

    /**
     * 发行地点
     */
    @Column(name = "publish_place")
    private String publishPlace;

    /**
     * 歌词
     */
    @Column(name = "lyric")
    private String lyric;


    /**
     * 歌曲类型1中文2英文
     */
    @Column(name = "music_type")
    private String musicType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;


}