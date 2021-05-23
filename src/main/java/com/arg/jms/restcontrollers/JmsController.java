package com.arg.jms.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arg.jms.services.JmsReceiverService;
import com.arg.jms.services.JmsSenderService;

@RestController
@RequestMapping("/jms")
public class JmsController {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	JmsReceiverService jmsReceiverService;
	
	@Autowired
	JmsSenderService jmsSenderService;
	
	@RequestMapping(value="/send/{message}", method= RequestMethod.GET)
	@ResponseBody
	public String sendMessage(@PathVariable(value="message") String message) {
		this.stopJmsListener();
		jmsSenderService.Send(message);
		return "Message Added To ActiveMQ " + message;
	}

	@RequestMapping(value="/pause", method= RequestMethod.GET)
	public String pauseJmsListener() {
	    this.stopJmsListener();
	    return "Stopped Jms Listener";
	}

	@RequestMapping(value="/restart", method=RequestMethod.GET)
	@ResponseBody
	public String restartJmsListener() {
		startJmsListener();
	    return "Jms Listener restarted";
	}
	
	@RequestMapping(value="/fetch", method=RequestMethod.GET)
	public String getMessageReceivedFromJms() throws InterruptedException {
		startJmsListener();
		Thread.currentThread().sleep(2000);
	    return JmsReceiverService.MessagesReceived.toString();
	}
	
	
	
	private void stopJmsListener() {
		JmsListenerEndpointRegistry customRegistry =
	            context.getBean(JmsListenerEndpointRegistry.class);
	    customRegistry.stop();
	}
	
	
	private void startJmsListener() {
		JmsListenerEndpointRegistry customRegistry =
	            context.getBean(JmsListenerEndpointRegistry.class);
	    customRegistry.start();
	}

}
