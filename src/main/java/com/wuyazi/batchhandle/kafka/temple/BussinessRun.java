package com.wuyazi.batchhandle.kafka.temple;

import com.alibaba.fastjson.JSON;
import com.wuyazi.batchhandle.music.service.MusicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName: BussinessRun
 * @Description: TODO
 * @Auther: YCKJ1374
 * @Date: 2019/9/16 11:23
 * @Version: 1.0
 */
@Component
public class BussinessRun {


    /**
     * 日志服务
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /** 线程池保持ALIVE状态线程数 */
    public static final int                 CORE_POOL_SIZE      = 10;

    @Autowired
    private MusicInfoService musicInfoService;


    public void handleData(List<MusicInfoDto> musicInfoDtos){

        Map<String, List<MusicInfoDto>> musicInfosMap = new HashMap<>();
        musicInfosMap = averageAssignMap(musicInfoDtos,CORE_POOL_SIZE);
        if( !CollectionUtils.isEmpty(musicInfosMap) ){
            for (Map.Entry<String,List<MusicInfoDto>> entry : musicInfosMap.entrySet()) {

                SystemThreadPool.getThread().execute(new MusicInfoRecord(entry.getValue(),entry.getKey()));

            }
        }


    }


    /**
     * 将一组数据平均分成n组
     *
     * @param source 要分组的数据源
     * @param n      平均分成n组
     * @param <T>
     * @return
     */
    public <T> Map<String,List<T>> averageAssignMap(List<T> source, int n) {
        Map<String,List<T>> result = new HashMap<>();
        //(先计算出余数)
        int remainder = source.size() % n;
        //然后是商
        int number = source.size() / n;
        //偏移量
        int offset = 0;
        for (int i = 0; i < n; i++) {
            List<T> value = new ArrayList<>();
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            if( !CollectionUtils.isEmpty(value)){
                result.put(i + 1 +"",value);
            }

        }
        return result;
    }



    class MusicInfoRecord implements Runnable {
        private List<MusicInfoDto> musicInfoDtos;
        private String name;

        public MusicInfoRecord(List<MusicInfoDto> musicInfoDtos,String name) {
            this.musicInfoDtos = musicInfoDtos;
            this.name = name;
        }

        @Override
        public void run() {

            try {

                long beginTime = System.currentTimeMillis();
                logger.info("线程信息 ------> {}", name);
                //先批量删除在批量入数据

                if( !CollectionUtils.isEmpty(musicInfoDtos) ){
                    musicInfoService.handleReceveData(musicInfoDtos);
                }

                long endTime = System.currentTimeMillis();
                logger.info("解析数据每个线程入库耗时=============================={}",  endTime - beginTime);

                musicInfoDtos = null;

            }catch (Exception e){
                logger.error("多线程数据批量入库异常 -----{}-> {}---{}---{}",JSON.toJSONString(musicInfoDtos),e.getMessage(),e.toString(),e.getStackTrace());
                e.printStackTrace();
            }
        }

        public List<MusicInfoDto> getMusicInfoDtos() {
            return musicInfoDtos;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "FeatureRecord [name=" + name + "]";
        }
    }


}
