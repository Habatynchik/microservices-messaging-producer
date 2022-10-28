package ua.epam.microservicesmessagingproducer.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.epam.microservicesmessagingproducer.model.entity.User;

@Log4j2
@Service
public class ProducerService {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }

    public Long sendMessageAndReceive(User user) {
        return (Long) rabbitTemplate.convertSendAndReceive(exchange, routingKey, user);
    }

}
