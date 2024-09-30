package com.paymentservice.paymentgateway.dtos;


import com.paymentservice.paymentgateway.models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetailsRequest {
    private String orderId;
    private String paymentId;
    private String paymentLink;
    private PaymentStatus status;
}
