package br.com.fiap.order_receiver.usecase.sender;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;

public interface IQueueSender {

    void send(CreateOrderDto createOrderDto);

}
