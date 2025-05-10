package br.com.fiap.order_receiver.usecase.sender;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;

public class SendToQueueUseCase implements IQueueSender {

    @Override
    public void send(CreateOrderDto createOrderDto) {

    }

}
