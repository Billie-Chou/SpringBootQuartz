package top.billie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import top.billie.quartz.QuartzDemo;
import top.billie.service.UsersService;

/**
 * @program: SpringBootScheduled
 * @description: Quartz配置类
 * @author: Billie
 * @create: 2019-06-30
 **/

@Configuration
public class QuartzConfig {

    /**
    * @Description: 创建JobDetailFactoryBean对象，关联job类
    * @Param: []
    * @return: org.springframework.scheduling.quartz.JobDetailFactoryBean
    * @Author: Billie
    * @Date: 2019/6/30
    */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        //关联job类
        factory.setJobClass(QuartzDemo.class);
        return  factory;
    }

    /**
    * @Description:  创建SimpleTriggerFactoryBean对象，关联JobDetailFactoryBean
    * @Param: [jobDetailFactoryBean]
    * @return: org.springframework.scheduling.quartz.SimpleTriggerFactoryBean
    * @Author: Billie
    * @Date: 2019/6/30
    */
/*    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        //关联JobDetailFactoryBean
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        //设置定时循环时间，单位为毫秒
        factory.setRepeatInterval(2000);
        //重复次数
        factory.setRepeatCount(5);
        return  factory;
    }*/

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        //关联JobDetailFactoryBean
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        //设置触发事件
        factory.setCronExpression("0/3 * * * * ?");
        return factory;
    }

    /**
    * @Description: 创建SchedulerFactoryBean对象，关联Trigger
    * @Param: []
    * @return: org.springframework.scheduling.quartz.SchedulerFactoryBean
    * @Author: Billie
    * @Date: 2019/6/30
    */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean
            ,MyAdaptableJobFactory myAdaptableJobFactory){
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联Triggers
        factory.setTriggers(cronTriggerFactoryBean.getObject());
        //设置新的JobDetailFactoryBean
        factory.setJobFactory(myAdaptableJobFactory);
        return factory;
    }
}
