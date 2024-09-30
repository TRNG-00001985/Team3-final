package com.revature.revshop.event;

import org.springframework.context.ApplicationEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.revature.revshop.model.OrderStatus; // Import the OrderStatus enum

@Getter
@Setter
public class OrderEvent extends ApplicationEvent {
    
    private String orderNumber;
    private String userId;
    private OrderStatus status; // Include the status

    public OrderEvent(Object source, String orderNumber, String userId, OrderStatus status) {
        super(source);
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.status = status; // Set the status
    }
}
