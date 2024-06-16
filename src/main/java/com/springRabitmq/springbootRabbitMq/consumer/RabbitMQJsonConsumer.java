package com.springRabitmq.springbootRabbitMq.consumer;

import com.springRabitmq.springbootRabbitMq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.jsonqueue.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String.format("Recieved JSON message -> %s",user.toString()));
    }
}
