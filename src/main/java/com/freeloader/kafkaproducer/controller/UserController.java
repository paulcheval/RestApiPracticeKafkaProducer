package com.freeloader.kafkaproducer.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freeloader.kafkaproducer.model.UserDetails;
import com.freeloader.kafkaproducer.producer.MessageProducer;


@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
	
	@Value(value = "${message.topic.name}")
	private String topicName;
	
	private MessageProducer messageProducer;
	
	public UserController(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}

	@PostMapping("")
	public ResponseEntity<String> createUser(@RequestBody UserDetails userDetails) {
		messageProducer.sendUserDetailMessage(topicName, userDetails);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
