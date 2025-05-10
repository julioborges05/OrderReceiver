package br.com.fiap.order_receiver.config;

import br.com.fiap.order_receiver.usecase.finder.FindOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinderConfig {

    @Bean
    FindOrderUseCase findOrderUseCase(FindOrderUseCase findOrderUseCase) {
        return findOrderUseCase;
    }

}
