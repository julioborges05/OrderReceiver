package br.com.fiap.order_receiver.usecase.finder;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import br.com.fiap.order_receiver.gateway.adapter.OrderService;
import org.springframework.stereotype.Service;

@Service
public class FindOrderUseCase implements IOrderFinder {

    private final OrderService orderService;

    public FindOrderUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderCreatedDto findById(Long id) {
        return orderService.getOrderById(id).getBody();
    }
}
