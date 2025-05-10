package br.com.fiap.order_receiver.controller;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;
import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import br.com.fiap.order_receiver.usecase.receiver.ReceiveFromQueueUseCase;
import br.com.fiap.order_receiver.usecase.sender.SendToQueueUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    private final SendToQueueUseCase queueSender;
    private final ReceiveFromQueueUseCase queueReceiver;

    public ReceiverController(SendToQueueUseCase queueSender, ReceiveFromQueueUseCase queueReceiver) {
        this.queueSender = queueSender;
        this.queueReceiver = queueReceiver;
    }

    @PostMapping
    public void createOrder(@RequestBody CreateOrderDto createOrderDto) {
        queueSender.send(createOrderDto);
    }

    @GetMapping
    public OrderCreatedDto getOrder() {
        return queueReceiver.receive();
    }

}
