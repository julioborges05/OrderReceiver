package br.com.fiap.order_receiver.usecase.receiver;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveFromQueueUseCase implements IQueueReceiver {

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    @Override
    public OrderCreatedDto receive(OrderCreatedDto orderCreatedDto) {
        return orderCreatedDto;
    }

}
