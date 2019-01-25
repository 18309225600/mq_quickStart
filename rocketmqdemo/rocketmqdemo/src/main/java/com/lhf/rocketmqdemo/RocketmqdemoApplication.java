package com.lhf.rocketmqdemo;

import com.lhf.rocketmqdemo.simple.producer.AsyncProducer;
import com.lhf.rocketmqdemo.simple.producer.OneWayProducer;
import com.lhf.rocketmqdemo.simple.producer.SyncProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
@RestController
public class RocketmqdemoApplication {

	@Autowired
	private SyncProducer syncProducer;

	@Autowired
	private AsyncProducer asyncProducer;

	@Autowired
	private OneWayProducer oneWayProducer;

	public static void main(String[] args) {
		SpringApplication.run(RocketmqdemoApplication.class, args);
	}


	@GetMapping("/syncProducer")
	public void syncProducer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
		syncProducer.defaultProducer();
	}

	@GetMapping("/asyncProducer")
	public void asyncProducer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
		asyncProducer.defaultProducer();
	}

	@GetMapping("/oneWayProducer")
	public void oneWayProducer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
		oneWayProducer.defaultProducer();
	}

}

