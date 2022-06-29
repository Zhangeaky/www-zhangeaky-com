package com.zhangeaky.application.web;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ConfigurationContainer {

    @Autowired
    MailService mailService;


    @Bean
    DefaultMQPushConsumer make() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setConsumerGroup("Application_group");
        try {
            consumer.subscribe("mail", "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }


        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                for (MessageExt msg:msgs) {

                    String s = new String(msg.getBody());
                    System.out.println("收到消息 ... " + s);
                    mailService.sendSimpleMail(s);
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;

    }
}
