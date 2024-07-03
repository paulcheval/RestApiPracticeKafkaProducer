package com.freeloader.kafkaproducer.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.freeloader.kafkaproducer.model.UserDetails;

@Component
public class MessageProducer {
	
	private KafkaTemplate<String, String> kafkaTemplate;
	private KafkaTemplate<String, UserDetails> kafkaUserDetailTemplate;
	
	public MessageProducer(KafkaTemplate<String, String> kafkaTemplate,
			KafkaTemplate<String, UserDetails> kafkaUserDetailTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaUserDetailTemplate = kafkaUserDetailTemplate;
	}
	
	public void sendMessage(String topic, String message) {
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
	             System.out.println("Sent message=[" + message + 
	                 "] with offset=[" + result.getRecordMetadata().offset() + "]" 
	            		 + " partition [" + result.getRecordMetadata().partition()  + "]" 
	                 + " topic [" + result.getRecordMetadata().topic() + "]"
	                 + " producer record [" + result.getProducerRecord().toString() + "]");
	         } else {
	             System.out.println("Unable to send message=[" + 
	                 message + "] due to : " + ex.getMessage());
	         }
		});
	}
	
	public void sendUserDetailMessage(String topic, UserDetails message) {
		CompletableFuture<SendResult<String, UserDetails>> future = kafkaUserDetailTemplate.send(topic, message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
	             System.out.println("Sent userDetails message=[" + message + 
	                 "] with offset=[" + result.getRecordMetadata().offset() + "]" 
	            		 + " partition [" + result.getRecordMetadata().partition()  + "]" 
	                 + " topic [" + result.getRecordMetadata().topic() + "]"
	                 + " producer record [" + result.getProducerRecord().toString() + "]");
	         } else {
	             System.out.println("Unable to send message=[" + 
	                 message + "] due to : " + ex.getMessage());
	         }
		});
	}

}
