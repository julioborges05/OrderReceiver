package br.com.fiap.order_receiver.controller;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;
import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import br.com.fiap.order_receiver.usecase.finder.FindOrderUseCase;
import br.com.fiap.order_receiver.usecase.sender.SendToQueueUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    private final SendToQueueUseCase queueSender;
    private final FindOrderUseCase findOrderUseCase;

    public ReceiverController(SendToQueueUseCase queueSender, FindOrderUseCase findOrderUseCase) {
        this.queueSender = queueSender;
        this.findOrderUseCase = findOrderUseCase;
    }

    @PostMapping
    public void createOrder(@RequestBody CreateOrderDto createOrderDto) {
        queueSender.send(createOrderDto);
    }

    @GetMapping("/{id}")
    public OrderCreatedDto getOrder(@PathVariable Long id) {
        return findOrderUseCase.findById(id);
    }

}
