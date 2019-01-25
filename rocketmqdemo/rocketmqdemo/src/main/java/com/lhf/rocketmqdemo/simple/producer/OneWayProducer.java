package com.lhf.rocketmqdemo.simple.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Author lhf
 * @Description
 * @Date 2019/1/25 13:40
 * @Version 1.0
 **/
@Component
public class OneWayProducer {

    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${apache.rocketmq.topic}")
    private String topic;

    public void defaultProducer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();

        Message message = new Message(topic,"OneWayProducer tags","OneWayProducer keys","OneWayProducer body".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.sendOneway(message);
    }
}
