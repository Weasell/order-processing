package orderprocessing;
import javax.persistence.*;

@Entity
@Table(name="SHIPPING_INFO") 
public class ShippingInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	String id;
	@Transient 
	String name ; 
	@Column(name="ADDRESS1")
	String addressLine1; 
	@Column(name="ADDRESS2")
	String addressLine2 ; 
	@Column(name="CITY")
	String city; 
	@Column(name="STATE")
	String state; 
	@Column(name="POSTAL_CODE")
	String zip;
	public ShippingInfo() {
		
	}
	public ShippingInfo(String name, String addressLine1, String addressLine2, String city, String state, String zip) {
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	} 
	
	

}
