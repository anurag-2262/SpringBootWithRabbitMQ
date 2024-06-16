package com.springRabitmq.springbootRabbitMq.publisher;

import com.springRabitmq.springbootRabbitMq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonPublisher {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routingKey.json.name}")
    private String routingJsonKey;
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonPublisher.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("json message sent to queue -> %s",user.toString()));
        rabbitTemplate.convertAndSend(exchange,routingJsonKey,user);
    }
}
