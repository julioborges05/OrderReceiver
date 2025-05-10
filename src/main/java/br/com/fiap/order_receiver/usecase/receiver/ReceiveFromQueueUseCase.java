package br.com.fiap.order_receiver.usecase.receiver;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;

public class ReceiveFromQueueUseCase implements IQueueReceiver {

    @Override
    public OrderCreatedDto receive() {
        return null;
    }

}
