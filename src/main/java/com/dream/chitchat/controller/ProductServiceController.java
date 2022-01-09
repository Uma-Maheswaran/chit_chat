package com.dream.chitchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.chitchat.model.Message;
import com.dream.chitchat.service.MessageService;

@RestController
public class ProductServiceController {
	@Autowired
	MessageService messageService;

	@RequestMapping(value = "/messages")
	public ResponseEntity<Object> getMessages() {
		return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMessage(@PathVariable("id") String id, @RequestBody Message message) {

		messageService.updateMessage(id, message);
		return new ResponseEntity<>("Message is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		messageService.deleteMessage(id);
		return new ResponseEntity<>("Message is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public ResponseEntity<Object> createMessage(@RequestBody Message message) {
		messageService.createMessage(message);
		return new ResponseEntity<>("Message is created successfully", HttpStatus.CREATED);
	}
}