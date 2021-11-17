package orderprocessing;

import javax.persistence.*;


@Entity
@Table(name="CUSTOMER_ORDER_LINE_ITEM") 
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	int row_sid;
	@Column(name="ITEM_NUMBER")
	int id;
	@Column(name="ITEM_NAME")
	String name;
	
	@Column(name="QUANTITY")
	int availableQuantity;
	
	@Transient 
	double price;
	
	@Transient 	
	String picURL;
	
	@Column(name="CUSTOMER_ORDER_ID_FK")
	int id_FK;
	
	public Item() {}
	
	public Item(int id, String name, int availQuan, double price, String picURL) {
		this.id = id;
		this.name = name;
		this.availableQuantity = availQuan;
		this.price = price;
		this.picURL = picURL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", availableQuantity=" + availableQuantity + ", price=" + price
				+ ", picURL=" + picURL + "]";
	}
	
	
	
	

}
