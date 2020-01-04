package com.atguigu.springboot_mp.rabbitmq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = "topic.woman")//监听的队列名称
@RabbitListener(queues = "fanout.A")//监听的队列名称

public class MessageReceive2 {

    @RabbitHandler
    public void process(Map testMessage, Channel channel, Message message) {
        System.out.println("DirectReceiver消费者收到消息 MessageReceive2 : " + testMessage.toString());
        //告诉服务器接收到消息  已经被我消费了，可以在队列中删除，否则消息服务器 认为这条消息没处理，后续还会再发。
        try {
            Thread.sleep(10000);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receive success" );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }




}
