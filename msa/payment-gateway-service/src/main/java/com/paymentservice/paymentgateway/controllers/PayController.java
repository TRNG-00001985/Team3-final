package com.paymentservice.paymentgateway.controllers;

import com.paymentservice.paymentgateway.dtos.PaymentDetailsRequest;
import com.paymentservice.paymentgateway.models.PaymentDetails;
import com.paymentservice.paymentgateway.services.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PayController {

    private final PayService paymentService;

    @Autowired
    public PayController(PayService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Inserts a new PaymentDetails record into the database.
     *
     * @param paymentDetailsRequest The request object containing payment details.
     * @return ResponseEntity containing the saved PaymentDetails and HTTP status.
     */
    @PostMapping("/insert")
    public ResponseEntity<PaymentDetails> insertPaymentDetails(
            @RequestBody PaymentDetailsRequest paymentDetailsRequest) {
        
        // Call the service to insert PaymentDetails
        PaymentDetails paymentDetails = paymentService.insertPaymentDetails(
                paymentDetailsRequest.getOrderId(),
                paymentDetailsRequest.getPaymentId(),
                paymentDetailsRequest.getPaymentLink(),
                paymentDetailsRequest.getStatus()
        );
        
        // Return the saved PaymentDetails with HTTP 201 Created status
        return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }
}
