package com.molin.project200908.common;

import com.molin.project200908.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 86134
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class DeleteImgs {
    @Autowired
    private RedisUtil redisUtil;

    //3.添加定时任务
//    Cron表达式参数分别表示：
//
//    秒（0~59） 例如0/5表示每5秒
//    分（0~59）
//    时（0~23）
//    日（0~31）的某天，需计算
//    月（0~11）
//    周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）

//    @Scheduled(cron = "0/10 * * * * ?")

    //    @Scheduled：除了支持灵活的参数表达式cron之外，还支持简单的延时操作，例如 fixedDelay ，fixedRate 填写相应的毫秒数即可。
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        String path = "/C:/Users/86134/IdeaProjects/project200907/src/main/resources/static/imgs";
        File file = new File(path);
        String[] list = file.list();
        for (String s : list) {
            redisUtil.sSet("localImgs", s);
        }
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        Set<String> difSet = redisUtil.difSet("localImgs", "dataBaseImgs");
        for (String s : difSet) {
            redisUtil.setRemove("localImgs", s);
            new File("/C:/Users/86134/IdeaProjects/project200907/src/main/resources/static/imgs/" + s).delete();
            System.out.println("删除"+s);
        }
    }
}
