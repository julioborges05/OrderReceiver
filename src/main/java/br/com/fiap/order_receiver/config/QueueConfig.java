package br.com.fiap.order_receiver.config;

import br.com.fiap.order_receiver.usecase.sender.SendToQueueUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    SendToQueueUseCase queueSender(SendToQueueUseCase sendToQueueUseCase) {
        return sendToQueueUseCase;
    }

}
