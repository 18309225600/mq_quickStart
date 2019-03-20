package com.lhf.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;


/**
 * @Author lhf
 * @Description
 * @Date 2019/3/20 10:57
 * @Version 1.0
 **/
@Configuration
public class AmqConfig {

    @Bean(name = "counting")
    public Queue counting(){
        return new ActiveMQQueue("com.lhf.queue.counting");
    }


    @Bean(name = "newRiskMessage")
    public Queue newRiskMessage(){
        return new ActiveMQQueue("com.lhf.queue.newRiskMessage");
    }
}
