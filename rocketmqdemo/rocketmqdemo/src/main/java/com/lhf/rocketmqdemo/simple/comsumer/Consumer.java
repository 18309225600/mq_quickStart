package com.lhf.rocketmqdemo.simple.comsumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author lhf
 * @Description
 * @Date 2019/1/25 13:41
 * @Version 1.0
 **/
@Component
public class Consumer implements InitializingBean {
    @Value("${apache.rocketmq.consumer.consumerGroup}")
    private String pushConsumer;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${apache.rocketmq.topic}")
    private String topic;

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultConsumer();
    }

    private void defaultConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(pushConsumer);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.subscribe(topic,"*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }
}
