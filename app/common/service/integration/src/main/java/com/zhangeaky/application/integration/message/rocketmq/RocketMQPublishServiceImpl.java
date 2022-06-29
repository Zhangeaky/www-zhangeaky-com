package com.zhangeaky.application.integration.message.rocketmq;

import com.zhangeaky.application.integration.message.MessagePublishService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RocketMQPublishServiceImpl implements MessagePublishService {

    @Autowired
    DefaultMQProducer defaultMQProducer;

    @PostConstruct
    private void initProducer() {
        try {
            defaultMQProducer.setMqClientApiTimeout(3600*1000);
            defaultMQProducer.setSendMsgTimeout(3600*1000);
            defaultMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean publish(String payload) {

        Message msg = new Message();
        msg.setTopic("mail");
        msg.setWaitStoreMsgOK(true);
        msg.setTags("A");
        msg.setBody(payload.getBytes());

        try {
            defaultMQProducer.send(msg);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("[message has been pulished ... ]");

        return true;
    }


    @Override
    public boolean AsyncPublish() {

        //defaultMQProducer.send();

        return MessagePublishService.super.AsyncPublish();
    }
}
