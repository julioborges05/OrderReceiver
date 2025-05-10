package br.com.fiap.order_receiver.config;

import br.com.fiap.order_receiver.gateway.adapter.OrderService;
import br.com.fiap.order_receiver.usecase.finder.FindOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinderConfig {

    @Bean
    FindOrderUseCase findOrderUseCase(OrderService orderService) {
        return new FindOrderUseCase(orderService);
    }

    @Bean
    OrderService orderService(OrderService orderService) {
        return orderService;
    }

}
