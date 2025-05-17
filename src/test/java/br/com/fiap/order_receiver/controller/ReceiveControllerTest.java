package br.com.fiap.order_receiver.controller;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;
import br.com.fiap.order_receiver.controller.dto.PaymentDto;
import br.com.fiap.order_receiver.controller.dto.ProductVOrderDto;
import br.com.fiap.order_receiver.domain.enums.StatusOrder;
import br.com.fiap.order_receiver.usecase.finder.FindOrderUseCase;
import br.com.fiap.order_receiver.usecase.sender.SendToQueueUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ReceiveControllerTest {

    private ReceiverController receiverController;

    @Mock
    private SendToQueueUseCase queueSender;

    @Mock
    private FindOrderUseCase findOrderUseCase;

    private MockMvc mockMvc;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        receiverController = new ReceiverController(queueSender, findOrderUseCase);

        mockMvc = MockMvcBuilders
                .standaloneSetup(receiverController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createOrder() throws Exception {
        ProductVOrderDto productOrder = new ProductVOrderDto(1L, 1, 100.0);
        PaymentDto payment = new PaymentDto("123", "CREDIT_CARD", "Name",
                "Date", 123);
        StatusOrder status = StatusOrder.ABERTO;

        CreateOrderDto createOrderDto = new CreateOrderDto(List.of(productOrder), 1L, payment, status);

        mockMvc.perform(
                post("/receiver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createOrderDto))
        );

        verify(queueSender, times(1)).send(createOrderDto);
    }

    @Test
    void getOrder() throws Exception {
        Long orderId = 1L;

        mockMvc.perform(
                get("/receiver/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        verify(findOrderUseCase, times(1)).findById(orderId);
    }

}
