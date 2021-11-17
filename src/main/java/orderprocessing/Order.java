package orderprocessing;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="CYSTINER_ORDER") 

public class Order {
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_ORDER_ID_FK")
	private List<Item> items;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	
	@Column(name="CUSTOMER_NAME")
	String name;
	
	@Column(name="CUSTOMER_EMAIL")
	String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SHIPPING_INFO_ID_FK")
	private ShippingInfo shippingInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PAYMENT_INFO_ID_FK")
	private PaymentInfo paymentInfo;
	
	@Column(name="STATUS")
	String status;
	
	public Order() {
		items = new ArrayList<Item>();
	}
	public Order(ArrayList<Item> items, ShippingInfo shippingInfo, PaymentInfo paymentInfo) {
		this.items = items;
		this.shippingInfo = shippingInfo;
		this.paymentInfo = paymentInfo;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	public int getId() {
		return id;
	}
	public ShippingInfo getShippingInfo() {
		return shippingInfo;
	}
	public void setShippingInfo(ShippingInfo shippingInfo) {
		this.shippingInfo = shippingInfo;
	}
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	@Override
	public String toString() {
		return "Order [items=" + items + "]";
	}
}
