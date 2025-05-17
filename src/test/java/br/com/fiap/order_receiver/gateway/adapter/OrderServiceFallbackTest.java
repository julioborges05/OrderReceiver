package br.com.fiap.order_receiver.gateway.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceFallbackTest {

    @Test
    void getOrderById() {
        OrderServiceFallback orderServiceFallback = new OrderServiceFallback();
        var response = orderServiceFallback.getOrderById(1L);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

}
