package com.atguigu.springboot_mp.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 *
 * 简单模式：一个生产者，一个消费者
 *
 * work模式：一个生产者，多个消费者，每个消费者获取到的消息唯一。
 *
 * 订阅模式：一个生产者发送的消息会被多个消费者获取。
 *
 * 路由模式：发送消息到交换机并且要指定路由key ，消费者将队列绑定到交换机时需要指定路由key
 *
 * topic模式：将路由键和某模式进行匹配，此时队列需要绑定在一个模式上，“#”匹配一个词或多个词，“*”只匹配一个词。

 **/
@RestController
public class SendMessageController implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    //直连交换机  指定exchange  和  routingKey
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);

//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback(this);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
      for(int i = 0;i<1000; i++) {
          rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
      }
        return "ok";
    }
//通配符交换器  指定交换器  和  一类topicRouting  例如：topic.#
    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);

        rabbitTemplate.convertAndSend("topicExchange", "topic.Routing", womanMap);
        return "ok";
    }

//广播交换器  指定交换器  不指定 topicRouting
    @GetMapping("/sendFanoutMessage3")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("queren消息已发送至消息队列！！！"+correlationData);
        System.out.println("queren消息已发送至消息队列！！！"+s);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("返回消息"+message );
        System.out.println("返回消息"+i );
        System.out.println("返回消息"+s );
        System.out.println("返回消息"+s1 );
    }
}

