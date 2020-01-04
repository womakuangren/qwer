package com.atguigu.springboot_mp.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
/**
  *bindings绑定队列
 * @QueueBinding value：绑定队列名称  exchange：配置交换器
 * @queue  value配置队列名称  autoDelete 是否是一个可以删除的队列，当所有消费者连接断开后是否自动删除队列。。
 * @Exchange  value交换器名称  type 交换器类型。autoDelete 当所有绑定队列都不在使用时是否删除交换器。
 * key： 路由键
 */
@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
//@RabbitListener(bindings=@QueueBinding(
//        value = @Queue(value = "TestDirectQueue",autoDelete = "false"),
//        exchange=@Exchange(value = "TestDirectExchange",type = ExchangeTypes.DIRECT,autoDelete = "true"),
//        key="TestDirectRouting"
//))
public class DirectReceiver1 {

    @RabbitHandler
    public void process(Map testMessage, Channel channel, Message message) throws InterruptedException {
        Thread.sleep(100);
        System.out.println("DirectReceiver消费者收到消息 MessageReceive1 : " + testMessage.toString());
        //告诉服务器接收到消息  已经被我消费了，可以在队列中删除，否则消息服务器 认为这条消息没处理，后续还会再发。
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receive success" );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
