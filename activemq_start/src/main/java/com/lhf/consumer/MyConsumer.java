package com.lhf.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author lhf
 * @Description
 * @Date 2019/3/20 11:06
 * @Version 1.0
 **/
@Component
public class MyConsumer {

    @JmsListener(destination = "com.lhf.queue.counting")
    public void receiveQueue(Message message) throws JMSException {
        TextMessage textMessage =(TextMessage)message;
        System.out.println("Consumer收到的报文为:"+textMessage.getText());
    }

}
