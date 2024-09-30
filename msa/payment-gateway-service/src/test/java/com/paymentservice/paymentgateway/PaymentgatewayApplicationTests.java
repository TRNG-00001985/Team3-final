package com.paymentservice.paymentgateway;

import com.paymentservice.paymentgateway.dtos.PaymentLinkRequestDto;
import com.paymentservice.paymentgateway.models.PaymentDetails;
import com.paymentservice.paymentgateway.models.PaymentStatus;
import com.paymentservice.paymentgateway.repositories.PaymentRepository;
import com.paymentservice.paymentgateway.services.PaymentGateway;
import com.paymentservice.paymentgateway.services.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentgatewayApplicationTests {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createLink_ShouldReturnPaymentLink_WhenOrderIsValid() {
        // Given
        String orderId = "order123";
        String expectedPaymentLink = "http://paymentlink.com/link";
        
        // Mock the payment gateway to return a payment link
        when(paymentGateway.createPaymentLink(any(PaymentLinkRequestDto.class)))
                .thenReturn(expectedPaymentLink);
        
        // When
        String paymentLink = paymentService.createLink(orderId);

        // Then
        assertEquals(expectedPaymentLink, paymentLink);
        verify(paymentRepository, times(1)).save(any(PaymentDetails.class));
        verify(paymentGateway, times(1)).createPaymentLink(any(PaymentLinkRequestDto.class));
    }

    @Test
    void getPaymentStatus_ShouldReturnStatus_WhenPaymentExists() {
        // Given
        String paymentId = "payment123";
        String orderId = "order123";
        PaymentStatus expectedStatus = PaymentStatus.SUCCESS;
        
        // Mock the repository and payment gateway
        PaymentDetails existingPaymentDetails = new PaymentDetails();
        existingPaymentDetails.setOrderId(orderId);
        
        when(paymentRepository.findByOrderId(orderId))
                .thenReturn(Optional.of(existingPaymentDetails));
        when(paymentGateway.getStatus(paymentId, orderId)).thenReturn(expectedStatus);
        
        // When
        PaymentStatus paymentStatus = paymentService.getPaymentStatus(paymentId, orderId);

        // Then
        assertEquals(expectedStatus, paymentStatus);
        verify(paymentRepository, times(1)).findByOrderId(orderId);
        verify(paymentGateway, times(1)).getStatus(paymentId, orderId);
        verify(paymentRepository, times(1)).save(any(PaymentDetails.class));
    }

    @Test
    void getPaymentStatus_ShouldThrowException_WhenPaymentDoesNotExist() {
        // Given
        String paymentId = "payment123";
        String orderId = "order123";
        
        // Mock the repository to return empty
        when(paymentRepository.findByOrderId(orderId))
                .thenReturn(Optional.empty());

        // Then
        assertThrows(RuntimeException.class, () -> {
            // When
            paymentService.getPaymentStatus(paymentId, orderId);
        });

        verify(paymentRepository, times(1)).findByOrderId(orderId);
        verify(paymentGateway, times(0)).getStatus(paymentId, orderId);  // Should not be called
        verify(paymentRepository, times(0)).save(any(PaymentDetails.class));  // Should not be called
    }
}
