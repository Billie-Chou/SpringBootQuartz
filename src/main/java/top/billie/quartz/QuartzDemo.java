package top.billie.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import top.billie.service.UsersService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: SpringBootScheduled
 * @description: Quartz定时
 * @author: Billie
 * @create: 2019-06-30
 **/
public class QuartzDemo implements Job {

    @Resource
    private UsersService usersService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("QuartzDemo.init:"+new Date());
        this.usersService.addUsers();
    }
}
