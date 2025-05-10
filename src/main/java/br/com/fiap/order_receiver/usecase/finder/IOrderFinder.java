package br.com.fiap.order_receiver.usecase.finder;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;

public interface IOrderFinder {

    OrderCreatedDto findById(Long id);

}
