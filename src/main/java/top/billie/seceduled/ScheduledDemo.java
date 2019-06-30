package top.billie.seceduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: SpringBootScheduled
 * @description:Scheduled定时方法
 * @author: Billie
 * @create: 2019-06-30
 **/
@Component
public class ScheduledDemo {

    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduleMethod(){
        System.out.println("schedule.init:"+new Date());
    }

}
