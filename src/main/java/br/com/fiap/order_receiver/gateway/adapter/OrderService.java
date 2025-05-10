package br.com.fiap.order_receiver.gateway.adapter;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "OrderService",
        url = "${order.service.url}",
        fallback = OrderServiceFallback.class)
public interface OrderService {

    @GetMapping(value = "/order/{orderId}")
    ResponseEntity<OrderCreatedDto> getOrderById(@PathVariable Long orderId);

}
