package org.junfalu.springboot.Indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Auther: lujunfa
 * @Date: 2018/11/15 10:17
 * @Description:根据需要实现自己的健康检测器，用于actuator报告中
 *
 */
@Component
public class MyOwnIndicator implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = check();
        if(errorCode!=0){
            return Health.down().withDetail("Error Code",errorCode).build();
        }

        return Health.up().build();
    }

    /**
     * 功能描述: 实现自己的监控报告
     * @auther: lujunfa
     * @param:
     * @return:
     * @date: 2018/11/15 10:19
     */
    private int check(){
        //对监控对象的监控操作
        return 0;
    }
}
