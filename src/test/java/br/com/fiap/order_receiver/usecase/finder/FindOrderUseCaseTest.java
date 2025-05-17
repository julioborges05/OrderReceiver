package br.com.fiap.order_receiver.usecase.finder;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;
import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import br.com.fiap.order_receiver.controller.dto.PaymentDto;
import br.com.fiap.order_receiver.controller.dto.ProductVOrderDto;
import br.com.fiap.order_receiver.domain.enums.StatusOrder;
import br.com.fiap.order_receiver.gateway.adapter.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

public class FindOrderUseCaseTest {

    private FindOrderUseCase findOrderUseCase;

    @Mock
    private OrderService orderService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        findOrderUseCase = new FindOrderUseCase(orderService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findById() {
        ProductVOrderDto productOrder = new ProductVOrderDto(1L, 1, 100.0);
        PaymentDto payment = new PaymentDto("123", "CREDIT_CARD", "Name",
                "Date", 123);
        StatusOrder status = StatusOrder.ABERTO;

        OrderCreatedDto orderCreatedDto = new OrderCreatedDto(List.of(productOrder), 1L, payment, status);

        when(orderService.getOrderById(1L))
                .thenReturn(ResponseEntity.ok(orderCreatedDto));

        var result = findOrderUseCase.findById(1L);

        assertInstanceOf(OrderCreatedDto.class, result);
        assertEquals(orderCreatedDto, result);
    }

}
