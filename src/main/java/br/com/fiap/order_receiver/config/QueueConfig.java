package br.com.fiap.order_receiver.config;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;
import br.com.fiap.order_receiver.usecase.sender.IQueueSender;
import br.com.fiap.order_receiver.usecase.sender.SendToQueueUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class QueueConfig {

    @Bean
    SendToQueueUseCase sendToQueueUseCase(KafkaTemplate<String, CreateOrderDto> kafkaTemplate) {
        return new SendToQueueUseCase(kafkaTemplate);
    }

}
