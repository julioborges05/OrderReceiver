package br.com.fiap.order_receiver.usecase.sender;

import br.com.fiap.order_receiver.controller.dto.CreateOrderDto;
import br.com.fiap.order_receiver.controller.dto.OrderCreatedDto;
import br.com.fiap.order_receiver.controller.dto.PaymentDto;
import br.com.fiap.order_receiver.controller.dto.ProductVOrderDto;
import br.com.fiap.order_receiver.domain.enums.StatusOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import scala.concurrent.Future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SendToQueueUseCaseTest {

    private SendToQueueUseCase sendToQueueUseCase;

    @Mock
    private KafkaTemplate<String, CreateOrderDto> kafkaTemplate;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        sendToQueueUseCase = new SendToQueueUseCase(kafkaTemplate);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void send() {
        ProductVOrderDto productOrder = new ProductVOrderDto(1L, 1, 100.0);
        PaymentDto payment = new PaymentDto("123", "CREDIT_CARD", "Name",
                "Date", 123);
        StatusOrder status = StatusOrder.ABERTO;

        CreateOrderDto createOrderDto = new CreateOrderDto(List.of(productOrder), 1L, payment, status);

        when(kafkaTemplate.send("create-order", createOrderDto))
                .thenReturn(CompletableFuture.completedFuture(mock(SendResult.class)));

        sendToQueueUseCase.send(createOrderDto);

        verify(kafkaTemplate, times(1)).send("create-order", createOrderDto);
    }

    @Test
    void send_shouldThrowRuntimeException_whenKafkaTemplateThrowsCheckedException() {
        ProductVOrderDto productOrder = new ProductVOrderDto(1L, 1, 100.0);
        PaymentDto payment = new PaymentDto("123", "CREDIT_CARD", "Name",
                "Date", 123);
        StatusOrder status = StatusOrder.ABERTO;

        CreateOrderDto createOrderDto = new CreateOrderDto(List.of(productOrder), 1L, payment, status);

        CompletableFuture<SendResult<String, CreateOrderDto>> future = new CompletableFuture<>();
        future.completeExceptionally(new ExecutionException("Kafka error", new RuntimeException()));

        when(kafkaTemplate.send("create-order", createOrderDto)).thenReturn(future);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sendToQueueUseCase.send(createOrderDto);
        });

        assertEquals("java.util.concurrent.ExecutionException: java.util.concurrent.ExecutionException: Kafka error", exception.getMessage());
        verify(kafkaTemplate, times(1)).send("create-order", createOrderDto);
    }

}
