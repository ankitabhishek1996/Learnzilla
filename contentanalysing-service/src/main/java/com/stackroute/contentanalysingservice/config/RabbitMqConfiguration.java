package com.stackroute.contentanalysingservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {



    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey2}")
    private String routingKey;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.queue2}")
    private String queue2;




    @Bean
    Queue queue2() {
        return new Queue(queue2, true);
    }


    @Bean
    Exchange myExchange() {
        /**
         * Add code to create Direct Exchange
         */
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }


//    @Bean
//    Binding bindingOne() {
//        /**
//         * Add code to bind queue and Exchange
//         */
//        return BindingBuilder
//                .bind(queue())
//                .to(myExchange())
//                .with(routingKey)
//                .noargs();
//    }

    @Bean
    Binding bindingTwo() {
        /**
         * Add code to bind queue and Exchange
         */
        return BindingBuilder
                .bind(queue2())
                .to(myExchange())
                .with(routingKey)
                .noargs();
    }


    @Bean
    ConnectionFactory connectionFactory() {

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }


    @Bean
    public MessageConverter jsonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}

