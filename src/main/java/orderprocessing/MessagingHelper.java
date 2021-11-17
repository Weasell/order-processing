package orderprocessing;


import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.*;
import javax.enterprise.context.RequestScoped;
import javax.inject.*;
import javax.jms.*;

@RequestScoped
@Resource(name = "jms/shipmentQCF", lookup = "jms/shipmentQCF", type = ConnectionFactory.class)
public class MessagingHelper {
	
	@Inject
	@JMSConnectionFactory("java:comp/env/jms/shipmentQCF")
	private JMSContext jmsContext;

	@Resource(lookup = "jms/shipmentQ")
	private Queue queue;
	
	public void initiateShipping() {
		String message = "Shipment Initiation Request from entity: BuildMyPhoneCase";
		System.out.println("Sending message: \t" + message);
		jmsContext.createProducer().send(queue, message);
		System.out.println("Message Sent!");
	}

}
