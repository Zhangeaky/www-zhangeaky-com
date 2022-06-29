package com.zhangeaky.application.web;

import com.zhangeaky.application.integration.message.MessagePublishService;
import org.apache.rocketmq.common.protocol.body.TopicList;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class SampleController {

    DefaultMQAdminExt defaultMQAdminExt;

    @Autowired
    MessagePublishService messagePublishService;

    @RequestMapping("/sample")
    public String doWork() {
        return "success";
    }

    @RequestMapping("/sendMail")
    public String sendMail(String mail) {

        return  String.valueOf(messagePublishService.publish(mail));

    }

    @RequestMapping
    public String metaInfo() {

        try{

            defaultMQAdminExt.setNamesrvAddr("localhost:9876");
            defaultMQAdminExt.start();
            TopicList topicList = defaultMQAdminExt.fetchAllTopicList();
            Set<String> topicList1 = topicList.getTopicList();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
