package br.com.fiap.order_receiver.controller.dto;

public record PaymentDto(
        String cardNumber,
        String cardType,
        String cardHolderName,
        String cardExpiryDate,
        int cvv){
}
