package br.com.fiap.order_receiver.usecase.receiver;

import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import br.com.fiap.order_receiver.controller.dto.PaymentDto;
import br.com.fiap.order_receiver.controller.dto.ProductVOrderDto;
import br.com.fiap.order_receiver.domain.enums.StatusOrder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiveFromQueueUseCaseTest {

    private final ReceiveFromQueueUseCase receiveFromQueueUseCase = new ReceiveFromQueueUseCase();

    @Test
    void receive() {
        ProductVOrderDto productOrder = new ProductVOrderDto(1L, 1, 100.0);
        PaymentDto payment = new PaymentDto("123", "CREDIT_CARD", "Name",
                "Date", 123);
        StatusOrder status = StatusOrder.ABERTO;

        OrderCreatedDto orderCreatedDto = new OrderCreatedDto(List.of(productOrder), 1L, payment, status);

        var result = receiveFromQueueUseCase.receive(orderCreatedDto);

        assertEquals(orderCreatedDto, result);
    }

}
