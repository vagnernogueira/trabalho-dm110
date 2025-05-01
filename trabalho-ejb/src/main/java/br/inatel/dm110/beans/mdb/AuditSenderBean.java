package br.inatel.dm110.beans.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;

@Stateless
public class AuditSenderBean {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/dm110queue")
	private Queue queue;

	public void sendTextMessage(String text) {
		try (JMSContext context = connectionFactory.createContext();) {
			TextMessage txtMsg = context.createTextMessage(text);
			context.createProducer().send(queue, txtMsg);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error sending message: " + text, e);
			e.printStackTrace();
		}
	}
	
	@Inject
	Logger log;
}
