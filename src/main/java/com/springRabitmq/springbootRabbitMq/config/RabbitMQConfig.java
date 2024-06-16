package com.springRabitmq.springbootRabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.jsonqueue.name}")
    private String jsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingKey.name}")
    private String routing_key;

    @Value("${rabbitmq.routingKey.json.name}")
    private String routingjsonkey;
     //spring bean for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    //queue for json maessages
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }
    //spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }
    //Binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routing_key);
    }
    //Binding between json queue and exchange using routing key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingjsonkey);
    }

    @Bean
    public MessageConverter  converter(){
        return new Jackson2JsonMessageConverter();
    }
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionfactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionfactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    //connectionFactory
    //RabbitTemplate
    //RabbitAdmin
    //above three beans are automatically configured by spring autoconfiguration
}
