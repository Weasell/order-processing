package orderprocessing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.*;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.client.*;
import javax.ws.rs.client.Entity;

@RequestScoped
@Path("order")
public class OrderProcessingService {
	@Inject
	MessagingHelper messagingHelper;

	private static String Pay_Proc_Post_URI = "http://localhost:9080/payment-processing/credit-card-processing/payment";
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitOrder(String jsonOrder) {
		// Use Jsonb to convert the JSON string to object structure
		Jsonb jsonb = JsonbBuilder.create();       
		Order submittedOrder = jsonb.fromJson(jsonOrder, Order.class); 
		// create entity manager using factory 
	    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("order-processing");
	    EntityManager entityManager = emfactory.createEntityManager();

	 // build a JsonObject for credit card payment processing
        entityManager.getTransaction().begin();
		entityManager.persist(submittedOrder);
		entityManager.getTransaction().commit();
//		---------------------------------------------	
		// build a JsonObject for credit card payment processing
		JsonObject jsonObj = Json.createObjectBuilder()
		       .add("entityName", "BuildYourPhoneCase")
		       .add("entityAccountNumber", "123456789")
		       .add("creditCardNumber", submittedOrder.getPaymentInfo().getCardNum())
		       .add("cardHolderName", submittedOrder.getPaymentInfo().getHolderName())
		       .add("expirationDate", submittedOrder.getPaymentInfo().getExpireDate())
		       .add("cvv", submittedOrder.getPaymentInfo().getCvvCode())
		       .build();
		        
		// process payment and get confirmation
		System.out.println("Calling bank to process payment ...");
		Client client = ClientBuilder.newClient();
		String paymentConfirm = client.target(Pay_Proc_Post_URI)
									  .request(MediaType.APPLICATION_JSON)
									  .post(Entity.json(jsonObj), String.class);
		client.close();
		System.out.println("Payment processing complete, confirmation# is: " + paymentConfirm);
		
		// Call initiateShipping() method on the messaging helper after the successful persistence of the customer order. 
		messagingHelper.initiateShipping();

//		---------------------------------------------		

		entityManager.close();
		emfactory.close();

		// nothing to do with submitted order for now        
		// just return a hard coded confirmation code   	
		System.out.println("Order received from customer: \n" + submittedOrder);
		 
		String confirmationNum = "985743w57" ; 
		return Response.ok(submittedOrder.getId()).build();
	}
}


