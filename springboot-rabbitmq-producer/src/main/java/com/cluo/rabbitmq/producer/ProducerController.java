package com.cluo.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author luolei
 * @Date 2019/8/20 9:27
 * @Description
 */
@RestController
public class ProducerController {

    private Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
    *@Description 简单消息模式和work模式
    *@Author  luolei
    *@Date 2019/8/20 10:26
    *@Param
    *@Return
    *@Exception
    *
    **/
    @GetMapping("/test/send1")
    public Map<String,Object> sendSimpleMsg(String message){
        Map<String,Object> result = new HashMap<>();
        logger.info("发送的消息内容为："+message);
        String routingKey = "simple_queue";
        rabbitTemplate.convertAndSend(routingKey,message);
        result.put("exec","S");
        result.put("code","10000");
        result.put("message","消息发送成功");
        return result;
    }

//    @GetMapping("/test/send2")
//    public Map<String,Object> sendPublicMessage(String message){
//        Map<String,Object> result = new HashMap<>();
//        String exchange = "test_exchange";
//        //String routingKey = "first_queue";
//        rabbitTemplate.convertAndSend(exchange,null,message);
//        result.put("exec","S");
//        result.put("code","10000");
//        result.put("message","消息发送成功");
//        return result;
//    }
}
