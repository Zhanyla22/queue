package com.example.queue.producer;

import com.example.queue.consumer.MessageReciever;
import com.example.queue.dto.request.ZakazRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Value("${spring.activemq.queue}")
    private String QUEUE_NAME;

    @Value("${time.delay}")
    private long TIME;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper mapper;

    private long delayMin=300000;

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageReciever.class);

    public void sendToQueue(String message) {
        LOGGER.info("смс отправлено '{}'", message);

        jmsTemplate.convertAndSend("request", message, message1 -> {
                    message1.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 10000);
//                    MessageProducer producer = session.createProducer(session.createQueue(" request"));
//                    producer.setDeliveryDelay(300000);
                    return message1;
                }
        );
        System.out.println("Ждите " + TIME + " милисекунд");
    }

    public String sendObjectToQueue(ZakazRequest zakazRequest, long delayMilli) throws JsonProcessingException {
        LOGGER.info("отправляется запрос на очередь" + "request");
        jmsTemplate.convertAndSend("request", mapper.writeValueAsString(zakazRequest)
                , message -> {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delayMilli);
            return message;
        });
        return "отправляется запрос на очередь";
    }
}
