package com.revature.revshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.revature.revshop.event.OrderEvent;
import com.revature.revshop.config.OrderEventDeserializer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Map;



@SpringBootApplication
public class NotificationServiceApplication {

	 @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapServers;



    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Bean
    public ConsumerFactory<String, OrderEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notificationId");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderEventDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory = 
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

	// Method to send email
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("durrr@demomailtrap.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
	}

	// Listen for Kafka events and send email
	@KafkaListener(topics = "notificationTopic")
	public void getOrderEvent(OrderEvent orderEvent) {
		System.out.println("Received event: " + orderEvent.toString());

		// Customize the email body with order details
		String emailBody = "Thank you for shopping with us!\n\n" +
				"Your order has been placed successfully. \n" +
				"Order Number: " + orderEvent.getOrderNumber() + "\n\n" +
				"We hope you are satisfied with our service.\n" +
				"Please reach out if you need any further assistance.\n\n" +
				"Best regards,\n" +
				"Rev Shop Team";

		// Send the email
		sendEmail("majaaz0014@gmail.com", "Rev Shop: Order Confirmation", emailBody);
	}
}
