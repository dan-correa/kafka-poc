package com.example.kafkaPoc.consumer;


import com.example.kafkaPoc.domain.ItemsPoc;
import com.example.kafkaPoc.repository.MessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {
    private Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics ="${myapp.kafka.topic}",groupId ="xyz")
    public void consume( @Payload String message, Acknowledgment ack) throws IOException {
        ack.acknowledge();
        JsonNode jsonNode = objectMapper.readValue(message, JsonNode.class);
        ItemsPoc item = new ItemsPoc();
        item.setId(jsonNode.get("id").asInt());
        item.setMessage(jsonNode.get("message").asText());
        messageRepository.addItemsPoc(item);
        log.info("Menssagem de id " +item.getId()+ "recebida no consumidor");
    }
}
