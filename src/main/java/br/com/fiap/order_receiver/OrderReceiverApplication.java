package br.com.fiap.order_receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderReceiverApplication.class, args);
	}

}
