package br.com.fiap.order_receiver.controller.dto;

import br.com.fiap.order_receiver.domain.enums.StatusOrder;

import java.util.List;

public record CreateOrderDto(
        List<ProductVOrderDto> products,
        Long clientId,
        PaymentDto payment,
        StatusOrder statusOrder) {
}