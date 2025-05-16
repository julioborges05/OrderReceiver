package br.com.fiap.order_receiver.usecase.sender;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SendToQueueUseCase implements IQueueSender {

    private final KafkaTemplate<String, CreateOrderDto> kafkaTemplate;

    public SendToQueueUseCase(KafkaTemplate<String, CreateOrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(CreateOrderDto createOrderDto) {
        try {
            Future<?> future = kafkaTemplate.send("create-order", createOrderDto);

            future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
