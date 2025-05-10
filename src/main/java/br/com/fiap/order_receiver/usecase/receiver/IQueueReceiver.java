package br.com.fiap.order_receiver.usecase.receiver;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;

public interface IQueueReceiver {

    OrderCreatedDto receive(OrderCreatedDto orderCreatedDto);

}
