package com.arg.jms.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.arg.jms.dtos.Book;

@Service
public class JmsReceiverService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JmsReceiverService.class);
	
	public static List<String> MessagesReceived = new ArrayList<>();
	
	@JmsListener(destination = "book.order.queue")
	public void receiveBook(Book book) {
		LOGGER.info("Message Received");
	}
	
	
	@JmsListener(destination = "arg.message.queue")
	public void fetchMessage(String message) {
		LOGGER.info("Message Received ==" +message);
		MessagesReceived.add(message);
	}
}
