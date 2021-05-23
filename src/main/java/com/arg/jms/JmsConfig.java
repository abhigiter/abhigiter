package com.arg.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJms
@EnableTransactionManagement
@Configuration
public class JmsConfig {
	
	@Bean
	public MessageConverter jacksonMessageConverter() {
		MappingJackson2MessageConverter converter =  new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory obj = new ActiveMQConnectionFactory();
		obj.setBrokerURL("tcp://localhost:61616");
		obj.setUserName("admin");
		obj.setPassword("admin");
		return obj;
	}
	
	 @Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setMessageConverter(jacksonMessageConverter());
		template.setDeliveryPersistent(true);
		template.setSessionTransacted(true);
		return template;
	} 
	
	@Bean
	public DefaultJmsListenerContainerFactory jmtListenerContainerFactory () {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setTransactionManager(jmsTransactionManager());
		factory.setReceiveTimeout((long) 5000000);
		factory.setErrorHandler(t -> {
			System.out.println("Handling Error in listener for messages error is =" +t.getMessage());
		});
		factory.setMessageConverter(jacksonMessageConverter());
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager jmsTransactionManager() {
		
		return new JmsTransactionManager(connectionFactory());
	}

}
