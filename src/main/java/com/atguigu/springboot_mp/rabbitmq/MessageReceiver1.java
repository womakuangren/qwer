package com.atguigu.springboot_mp.rabbitmq;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = "topic.man")//监听的队列名称
@RabbitListener(queues = "fanout.B")//监听的队列名称

public class MessageReceiver1 {

    @RabbitHandler
    public void process(Map testMessage, Channel channel, Message message) {
        System.out.println("DirectReceiver消费者收到消息  MessageReceive1 : " + testMessage.toString());
        System.out.println("抛出异常。。。。。");
        //int a = 20/0;

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









