package top.billie.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: SpringBootScheduled
 * @description:
 * @author: Billie
 * @create: 2019-06-30
 **/
@Component("myAdaptableJobFactory")
public class MyAdaptableJobFactory extends AdaptableJobFactory {

    /**
    * @Description: AutowireCapableBeanFactory可以将一个对象添加到SpringIOC容器中，并完成注入
    * @Param:
    * @return:
    * @Author: Billie
    * @Date: 2019/6/30
    */
    @Resource
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object obj = super.createJobInstance(bundle);
        //将obj对象放入SpringIOC容器中，并完成注入
        this.autowireCapableBeanFactory.autowireBean(obj);
        return obj;
    }
}
