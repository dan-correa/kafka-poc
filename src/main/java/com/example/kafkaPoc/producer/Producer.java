package com.example.kafkaPoc.producer;

import com.example.kafkaPoc.domain.ItemsPoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class Producer {

    private Logger log =  LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

   @Value("${myapp.kafka.topic}")
   private String topic;

    public void sendMessage(String message){
        log.info("Mensagem enviada do produtor -> " + message);

        kafkaTemplate.send(topic,message);
    }
}
