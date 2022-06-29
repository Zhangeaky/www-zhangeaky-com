package com.zhangeaky.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String mailFrom;

    public void sendSimpleMail(String to) {

        System.out.println("[consumer: ] send " + to);
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(mailFrom);
        //邮件接收人
        message.setTo(to);
        //邮件主题
        message.setSubject("Hello world");
        //邮件内容
        message.setText("This is a e-mail from rocketMQ");
        //发送邮件
        mailSender.send(message);
    }
}
