package com.paymentservice.paymentgateway.services;

import com.paymentservice.paymentgateway.models.PaymentDetails;
import com.paymentservice.paymentgateway.models.PaymentStatus;
import com.paymentservice.paymentgateway.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PayService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Method to insert a new PaymentDetails record into the database
    public PaymentDetails insertPaymentDetails(String orderId, String paymentId, String paymentLink, PaymentStatus status) {
        PaymentDetails paymentDetails = new PaymentDetails();
        
        // Setting values to the entity
        paymentDetails.setOrderId(orderId);
        paymentDetails.setPaymentId(paymentId);
        paymentDetails.setPaymentLink(paymentLink);
        paymentDetails.setStatus(status);

        // Saving the entity to the database
        paymentRepository.save(paymentDetails);

        return paymentDetails; // Return the saved entity, if needed
    }
}
