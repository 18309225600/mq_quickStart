package com.lhf.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @Author lhf
 * @Description
 * @Date 2019/3/20 11:00
 * @Version 1.0
 **/
@Component
@EnableScheduling
public class MyProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue counting;

    /*@Scheduled(fixedDelay = 3000)*/
    public void sendMessage(){
        jmsTemplate.send(counting, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("测试第一条消息");
                return textMessage;
            }
        });
    }
}
