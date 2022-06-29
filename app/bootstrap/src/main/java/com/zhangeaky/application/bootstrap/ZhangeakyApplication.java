package com.zhangeaky.application.bootstrap;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication( scanBasePackages = {"com.zhangeaky.application"})
@ImportResource(locations = "classpath:spring/*.xml")
public class ZhangeakyApplication implements BeanFactoryAware {

    private ConfigurableBeanFactory beanFactory;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ZhangeakyApplication.class, args);
        DefaultMQPushConsumer bean = new ZhangeakyApplication().beanFactory.getBean(DefaultMQPushConsumer.class);
        System.out.println("[spring bean] "+bean);

    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        this.beanFactory = (ConfigurableBeanFactory) beanFactory;

    }
}
