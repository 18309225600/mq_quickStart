package com.lhf.rocketmqdemo.simple.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
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
public class AsyncProducer {

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

        producer.setRetryTimesWhenSendAsyncFailed(0);
        Message message = new Message(topic,"AsyncProducerTags","AsyncProducerKeys","AsyncProducer bodys".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult.getMsgId()+"succ");
            }

            @Override
            public void onException(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
