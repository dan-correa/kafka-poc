package org.example.kafkaPoc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@EnableKafka
public class AcknowledgmentConfig {



    @Autowired
    @Lazy
    ConsumerFactory<String, String> factory;

    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties) {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactory(KafkaTemplate<?, ?> template){

        ConcurrentKafkaListenerContainerFactory<String, String> listener =
                new ConcurrentKafkaListenerContainerFactory<>();

        listener.setConsumerFactory(factory);

        // Não falhar, caso ainda não existam os tópicos para consumo
        listener.getContainerProperties().setMissingTopicsFatal(false);

        // Commit manual do offset
        listener.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        // Commits síncronos
        listener.getContainerProperties().setSyncCommits(Boolean.TRUE);

        return listener;
    }
}
