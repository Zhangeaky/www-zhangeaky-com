package com.zhangeaky.application.web;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


public class RocketMQHandler implements MessageHandler{

    @Autowired
    DefaultMQPushConsumer defaultMQPushConsumer;

    @Autowired
    MailService mailService;

    @PostConstruct
    private void init() {
        try {
            defaultMQPushConsumer.start();
            handle("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void handle(Object payload) {

        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                for (MessageExt msg:msgs) {
                    String s = Arrays.toString(msg.getBody());
                    System.out.println("收到消息 ... " + s);
                    mailService.sendSimpleMail(s);
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });




    }
}
