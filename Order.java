package csc212project11;

import java.time.LocalDate;

public class Order {
	private int orderId;
	private int customerId;
	private String prod_Ids;
	private double totalPrice;
	private LocalDate orderDate; // store as yyyy-MM-dd
	private String status; // pending, shipped, delivered, canceled
	private LinkedList<Integer> productIds; // store product IDs in order
	
	
	
	public Order(int orderId, int customerId,String prod_ids,double totalPrice, LocalDate orderDate, String status) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.prod_Ids=prod_ids;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.status = status;
		this.productIds = new LinkedList<Integer>();
		addIds(prod_ids);
		}
	
	
	
	public void addIds(String ids) {
		String a[]= ids.split(";");
		for(int i=0;i<a.length;i++)
			productIds.addLast(Integer.parseInt(a[i].trim()));
	}
	
	public void addId(int id) {
		productIds.addLast(id);
	}
	
	
	
	
	public void updateOrder(Order ord) {
		this.orderId = ord.orderId;
		this.customerId = ord.customerId;
		this.prod_Ids=ord.prod_Ids;
		this.totalPrice = ord.totalPrice;
		this.orderDate = ord.orderDate;
		this.status = ord.status;
		this.productIds = ord.productIds;
	}
	
	

	

	//SETTERS AND GETTERS

	public int getOrderId() {return orderId;}

	public int getCustomerId() {return customerId;}
	public String getprod_Ids() {return prod_Ids; }

	public LinkedList<Integer> getProductIds() {return productIds;}

	public double getTotalPrice() {return totalPrice;}

	public LocalDate getOrderDate() {return orderDate;}

	public String getStatus() {return status;}

	

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public void display() {

		System.out.println("Order ID: "+orderId);
		System.out.println("Customer ID: "+customerId);
		System.out.println("Product IDs: ");
		productIds.display();
		System.out.println("");
		System.out.println("Total Price: "+totalPrice);
		System.out.println("Date: "+orderDate);
		System.out.println("Status: "+status);
		System.out.println("------------------------------------");

	}
	

}