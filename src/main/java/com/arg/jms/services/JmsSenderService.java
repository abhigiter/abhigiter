package com.arg.jms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.arg.jms.dtos.Book;

@Service
public class JmsSenderService {

	private static final String BOOK_QUEUE = "book.order.queue";
	private static final String ARG_MESSAGE_QUEUE = "arg.message.queue";
	
	@Autowired
	private JmsTemplate template;
	
	private static final Logger logger = LoggerFactory.getLogger(JmsSenderService.class);

	public void SendBook(Book book) {
		template.convertAndSend(BOOK_QUEUE, book);
	}
	
	public void Send(String message) {
		logger.info("Message Added To Queue " + message);
		template.convertAndSend(ARG_MESSAGE_QUEUE, message);
	}
}
