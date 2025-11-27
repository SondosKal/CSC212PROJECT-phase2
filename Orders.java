package csc212project11;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Orders {
	
	private AVL<Order> all_orders;
	private Customers all_Customers;
	static DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");

	
	public Orders(AVL<Customer> input_customers, AVL<Order> all_orders) {
		all_Customers=new Customers(input_customers);
		this.all_orders= all_orders;
	}
	
	public Orders() {
		all_Customers=new Customers();
		all_orders=new AVL<>();
	}
	
	public AVL<Order> get_Orders(){
		return all_orders;
	}
	
	public Order searchOrderById(int id) {
		if(all_orders.empty())return null;
		
		 boolean found=all_orders.findKey(id);
	        if(found)
	            return all_orders.retrieve();
	        else
		return null;
	}
	
	public void assign(Order ord) {
		Customer p= all_Customers.searchById(ord.getCustomerId());
		if(p==null)
			System.out.println("the customer does not exist to assign the order" );
		else p.addOrder(ord);
	}
	
	
	public void addOrder(Order ord) {
		if(searchOrderById(ord.getOrderId())==null) {
			all_orders.insert(ord.getOrderId(), ord);
			assign(ord);
		} else {
			System.out.println("Order with ID "+ ord.getOrderId()+ " ALREADY EXISTS!!");
		}
	}
	
	
	
	
	
	public static Order convert_String_to_order(String Line) {
		
		String a[]= Line.split(",");
		
		int OrderId=Integer.parseInt(a[0].trim().replaceAll("\"", ""));
		
		int customerId=Integer.parseInt(a[1].trim().replaceAll("\"", ""));
		
		String productIds=a[2].trim().replaceAll("\"", "");
		double totalPrice= Double.parseDouble(a[3]);
		LocalDate date=LocalDate.parse(a[4],df) ;
		String status= a[5].trim();
		
		Order ord= new Order(OrderId,customerId,productIds,totalPrice,date,status);
		return ord;
	}
	
	
	
	
	public void loadOrders(String fileName) {
		try {
			File f= new File(fileName);
			Scanner read = new Scanner(f);
			DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			System.out.println("READING THE FILE: "+ fileName);
			System.out.println("-----------------------------------");
			read.nextLine();
			while(read.hasNextLine()) {
				String line=read.nextLine().trim();
				Order ord = convert_String_to_order(line);
				addOrder(ord);
			}
			read.close();
			System.out.println("FILE LOADED SUCCESSFULLY!!!\n");
			
			}catch(Exception e) {
				System.out.println("ERROR LOADING ALL ORDERS: "+ e.getMessage());
			}
}
	
	
	public void displayAllOrders() {
		if (all_orders.empty()) {
			System.out.println("NO ORDERS FOUND");
			return;
		}
		
		else{
	           inOrder_all_Orders(all_orders.getRoot());
	         }
	}
	
	
	
	private void inOrder_all_Orders(BSTNode<Order>c)
	{
	if(c==null) return;
	inOrder_all_Orders(c.left);   
              
                   c.data.display();                   
                    System.out.println("***************************");
                    System.out.println("***************************");
           
	inOrder_all_Orders(c.right);
	}
	
}
