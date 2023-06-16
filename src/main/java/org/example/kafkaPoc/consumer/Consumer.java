package org.example.kafkaPoc.consumer;


import org.example.kafkaPoc.domain.ItemsPoc;
import org.example.kafkaPoc.repository.MessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class Consumer {
    private Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    ObjectMapper objectMapper;

    @RetryableTopic(
            attempts = "5",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            backoff = @Backoff(delay = 20000, multiplier = 2.0),
            exclude = {SerializationException.class, DeserializationException.class}

    )
    @KafkaListener(topics ="${myapp.kafka.topic}",groupId ="xyz")
    public void consume( @Payload String message, Acknowledgment ack) throws IOException {

        JsonNode jsonNode = objectMapper.readValue(message, JsonNode.class);
        ItemsPoc item = new ItemsPoc();
        item.setId(Optional.of(jsonNode.get("id").asInt()));
        item.setMessage(jsonNode.get("message").asText().describeConstable());
        messageRepository.addItemsPoc(item);
        log.info("Message id {} reciveid on the consumer", item.getId());
        ack.acknowledge();



    }

    @DltHandler
    public void dlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("{} from {}", in, topic);
    }

    private boolean isInt(String str){
        try{
            Integer.parseInt(str);
        }catch(Exception e ){
            return false;
        }
        return true;
    }
}

