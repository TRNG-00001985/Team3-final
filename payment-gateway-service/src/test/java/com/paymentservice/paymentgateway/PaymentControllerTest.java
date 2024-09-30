package com.paymentservice.paymentgateway;

import com.paymentservice.paymentgateway.controllers.PaymentController;
import com.paymentservice.paymentgateway.models.PaymentStatus;
import com.paymentservice.paymentgateway.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    void createPaymentLink_ShouldReturnPaymentLink_WhenValidOrderId() throws Exception {
        // Given
        String orderId = "order123";
        String expectedPaymentLink = "http://paymentlink.com/link";
        
        // Mock the payment service to return a payment link
        when(paymentService.createLink(orderId)).thenReturn(expectedPaymentLink);

        // When & Then
        mockMvc.perform(post("/payment/createLink")
                .param("orderId", orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedPaymentLink));

        verify(paymentService, times(1)).createLink(orderId);
    }


}
