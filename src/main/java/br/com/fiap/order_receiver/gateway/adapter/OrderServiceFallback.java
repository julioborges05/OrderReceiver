package br.com.fiap.order_receiver.gateway.adapter;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallback implements OrderService {

    @Override
    public ResponseEntity<OrderCreatedDto> getOrderById(Long orderId) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
