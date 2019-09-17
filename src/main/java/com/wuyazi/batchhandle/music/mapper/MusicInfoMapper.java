package com.wuyazi.batchhandle.music.mapper;

import com.wuyazi.batchhandle.music.domain.MusicInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: MusicInfoMapper
 * @Description: TODO
 * @Auther: an
 * @Date: 2019/8/13 17:26
 * @Version: 1.0
 */
@Mapper
public interface MusicInfoMapper extends CWMapper<MusicInfo>{

    /**
     * 如果添加了下面的两个注解的话 配置文件中不用写具体的select标签   但是要注意配置文件要放在 resources文件夹下
     * @param musicInfo
     * @return
     */
    List<MusicInfo> selectMusicInfos(MusicInfo musicInfo);

    /**
     * 批量插入
     * @param musicInfos
     * @return
     */
    void batchInsertMusicInfos(List<MusicInfo> musicInfos);
}
