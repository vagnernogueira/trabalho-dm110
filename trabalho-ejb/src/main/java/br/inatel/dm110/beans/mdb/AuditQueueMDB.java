package br.inatel.dm110.beans.mdb;

import java.util.logging.Logger;

import br.inatel.dm110.beans.AuditBean;
import br.inatel.dm110.dto.AuditDTO;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;


/**
 * Command to create Queue
 * ./jboss-cli.sh --connect --command="jms-queue add --queue-address=dm110queue --durable=true --entries=[java:/jms/queue/dm110queue]"
 * */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110queue") })
public class AuditQueueMDB implements MessageListener {

	@EJB
	private AuditBean auditBean;

	@Override
	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				String text = txtMessage.getText();
				log.info("Received audit message from queue: " + text);
				AuditDTO auditDTO = new AuditDTO(text);
				auditBean.create(auditDTO);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Inject
	Logger log;
}
