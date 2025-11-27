package csc212project11;

import java.time.LocalDate;
import java.util.Scanner;


public class Store {
	
	private static AVL<Customer> customers_list;
	private static AVL<Order> orders_list;
	private static AVL<Product> products_list;
	private static AVL<Review> reviews_list;
	
	private static Reviews all_Reviews;
	private static Customers all_Customers;
	private static Orders all_Orders;
	private static Products all_Products;
	
	
    private static AVLtypes<String,Customer> customers_sorted_by_name=new AVLtypes<>();
    private static AVLtypes<Double,Product> products_keyed_by_AVG_Rate=new AVLtypes<>();

    private static int n=3;

	
	public Store() {
		
		customers_list= new AVL<Customer>();
		orders_list= new AVL<Order>();
		products_list= new AVL<Product>();
		reviews_list= new AVL<Review>();
		
		
		all_Products= new Products(products_list);
		all_Customers= new Customers(customers_list);
		all_Orders=new Orders(customers_list,orders_list);	
		all_Reviews= new Reviews(reviews_list,products_list,customers_list);

	}
	
	public static void Load_all() {
		all_Products.load_products("C:\\Users\\sondo\\eclipse-workspace\\csc212project11\\prodcuts.csv");
	    all_Customers.loadCustomers("C:\\Users\\sondo\\eclipse-workspace\\csc212project11\\customers.csv");
	    all_Orders.loadOrders("C:\\Users\\sondo\\eclipse-workspace\\csc212project11\\orders.csv");
	    all_Reviews.load_reviews("C:\\Users\\sondo\\eclipse-workspace\\csc212project11\\reviews.csv");
	}

	public static void add_Customer(Customer c) {
	    if (all_Customers != null)
	        all_Customers.addCustomer(c);
	    else
	        System.out.println("Error: Customer list not initialized!");
	}

	public static void add_Product(Product p) {
	    if (all_Products != null)
	    	all_Products.addProduct(p);
	    else
	        System.out.println(" Error: Product list not initialized!");
	}

	public static void add_Order(Order o) {
	    if (all_Orders != null)
	        all_Orders.addOrder(o);
	    else
	        System.out.println("Error: Orders list not initialized!");
	}

	public static void add_Review(Review r) {
	    all_Reviews.addReview(r);
	}
	
	//////////////////////////////////
///////////////////[3]/////////////////////////////////
	
	public void displayTop3Products() {
	    if (products_list.empty()) {
	        System.out.println("No products available.");
	        return;
	    }
	    
	    store_Products_sorted(products_list.getRoot());
        products_in_order_by_Average(products_keyed_by_AVG_Rate.getRoot());
	}
	 
	private static void store_Products_sorted(BSTNode<Product>p)
    {
    if (p == null) return;   
        store_Products_sorted(p.left);
        products_keyed_by_AVG_Rate.insert(p.data.getAverageRating(),p.data);
       store_Products_sorted(p.right);
    }
   private static void products_in_order_by_Average(BST_node<Double,Product>p)
   {
       if (p == null||n==1) return; 
        products_in_order_by_Average(p.right);       
        {
            if(n>=1){
            System.out.println("product no"+(n)+" Product ID: " +p.data.getProductId()
                + " | Name: " +p.data.getName()
                + " | Avg Rating: " + String.format("%.2f",p.data.getAverageRating()));
            n--;
            }
        }
        products_in_order_by_Average(p.left);
   }
   
   ////////////////////////////////////////////////////////////
   //////////////////////////[4]////////////////////////
   public static void display_customers_soreted()
   {
       System.out.println("all customers sorted are :");
       store_customers_sorted(customers_list.getRoot());
       customers_in_order_by_name(customers_sorted_by_name.getRoot());
   }
	    
   private static void store_customers_sorted(BSTNode<Customer>p)
   {
   if (p == null) return;   
       store_customers_sorted(p.left);
        customers_sorted_by_name.insert(p.data.getName(),p.data);
      store_customers_sorted(p.right);
   }
  private static void customers_in_order_by_name(BST_node<String,Customer>p)
  {
      if (p == null) return; 
      customers_in_order_by_name(p.left);
      //p.data.display();
     System.out.println(p.key);
       customers_in_order_by_name(p.right);
  }
  
  
  ///////////////////////////////////////////////////////////
  //////////////////////[5]//////////////////////////////////
  public static void display_all_customers_who_reviewed_prod(int id)
  {
     Product p=all_Products.Search_Product_by_id(id);
     AVL<Review>product_Reviews=p.getReviews(); 
     display_customers_in_order(product_Reviews.getRoot());
  }
  private static void display_customers_in_order(BSTNode<Review>p)
  {
  if(p==null) return ;
    display_customers_in_order(p.left);
      System.out.println(p.key);
    display_customers_in_order(p.right);
  }

	///////////////////////////////////////////////////////////////
	/////////////////////////[1]/////////////////////////////////
	public static void displayAllOrders_between2dates(LocalDate d1, LocalDate d2) {
	    if (orders_list.empty()) {
	        System.out.println("No orders found!");
	        return;
	    }
	    
	    inOrder_all_Orders(orders_list.getRoot(),d1,d2);
	    System.out.println("----------------------------------------------------------");

	   
	}
	private static void inOrder_all_Orders(BSTNode<Order>c,LocalDate d1,LocalDate d2) {
		if(c==null)return;
		inOrder_all_Orders(c.left,d1,d2);
		Order o=c.data;
		if(o.getOrderDate().compareTo(d1)>0 && o.getOrderDate().compareTo(d2)<0) {
			System.out.println(o.getOrderId());
		}
		inOrder_all_Orders(c.right,d1,d2);
	}
	
	////////////////////////////////////////////////////////
	////List All Products Within a Price Range. [2]////////////
	public static void displayProductsInRange(double minPrice, double maxPrice) {
	    if (products_list.empty()) {
	        System.out.println("No products found!");
	        return;
	    }
	    System.out.println("Products with price in range [" + minPrice + ", " + maxPrice + "]:");  
	    inOrderRange(products_list.getRoot(), minPrice, maxPrice);
	        
	    System.out.println("--------------------------------------------------------------------------");
	}
	  

	    private static void inOrderRange(BSTNode<Product> node, double minPrice, double maxPrice) {
	        if (node == null) return;   
	       inOrderRange(node.left, minPrice, maxPrice);
	       
	        double price = node.data.getPrice();
	        if (price >= minPrice && price <= maxPrice) {
	            node.data.display();
	        }        
	            inOrderRange(node.right, minPrice, maxPrice);        
	    }
	    
	    
	    
	    
	

	public static void main(String[] args) {
		
		Store s= new Store();
		Scanner input= new Scanner(System.in);
		int choice=0;
		do {
			System.out.println("=============================WELCOME TO OUR STORE============================");

			System.out.println("1.Read data from file.");
			System.out.println("2.Add product.");
			System.out.println("3.Update Product");
			System.out.println("4.search Product by ID.");
			System.out.println("5.Register a new customer.");
			System.out.println("6.search Customer by ID.");
			System.out.println("7.Display Customer's order history.");
			System.out.println("8.All orders between two dates.");
			System.out.println("9.Top 3 products by average rating.");
			System.out.println("10.Display Customers Sorted Alphabitically.");
			System.out.println("11.diplay all Customers who reviewed a product.");
			System.out.println("12.Display All Customers");
			System.out.println("13.Display All Orders");
			System.out.println("14.Display All Products");
			System.out.println("15.Display All Reviews");
			
			
			System.out.println("16.Place an order.");
			System.out.println("17.Add a review.");
			System.out.println("18.Extract Reviews from a specific customer for all products.");			
			System.out.println("19.Track out of stock products.");
			System.out.println("20.Display reviews for a specific Product");
			System.out.println("21.Cancel an order.");
			System.out.println("22.Update an order status.");
			System.out.println("23.search Order by ID.");
			System.out.println("24.Edit a review.");
			System.out.println("25.Get an Average rating for a product.");		
			System.out.println("0.Exit ");
			System.out.print("----------------------------------");
			System.out.print("Enter your choice: ");
			choice=input.nextInt();


			switch(choice) {
			case 1:
				Load_all();
				break;
				
			case 2: 
				System.out.println("enter the product's ID: ");
				int pid=input.nextInt();
				input.nextLine();
				System.out.println("enter the product's NAME: ");
				String pname=input.nextLine();
				System.out.println("enter the product's PRICE: ");
				double price=input.nextDouble();
				System.out.println("enter the product's STOCK: ");
				int stock=input.nextInt();
				Product p=new Product(pid,pname,price, stock);
				add_Product(p);
				break;
				
				
			case 3:System.out.println("Enter product ID you want to update: ");
			int oldID= input.nextInt();
				Product up= all_Products.Search_Product_by_id(oldID);
				System.out.print("Enter new product Price: ");
				double newPrice= input.nextDouble();
				up.setPrice(newPrice);
				break;	
				
			case 4:
				System.out.println("Enter the product ID you want to search about :");
				int srchprd=input.nextInt();
				all_Products.Search_Product_by_id(srchprd).display();
				break;	
				
				
			case 5:
				System.out.println("enter the customer's ID: ");
				int cid=input.nextInt();
				input.nextLine();
				System.out.println("enter the customer's NAME: ");
				String cname=input.nextLine();
				System.out.println("enter the customer's EMAIL: ");
				String email=input.nextLine();
				
				Customer c = new Customer(cid, cname, email);
				add_Customer(c);
				break;
				
			case 6: 
				System.out.println("Enter the Customer ID you want to search about :");
				int srchcus=input.nextInt();
				all_Customers.searchById(srchcus).display();
				break;

            case 7:
				System.out.println("Enter customer ID you want order history for: ");
				int cIDh=input.nextInt();
				all_Customers.searchById(cIDh).displayOrders();
				break;
				
			case 8: 
				System.out.println("Enter the first date: ");
				input.nextLine();
				String d1=input.nextLine();
				input.nextLine();
				LocalDate date1 = LocalDate.parse(d1);
				System.out.println("Enter the Second date: ");
				String d2=input.nextLine();
				LocalDate date2 = LocalDate.parse(d2);
				
				Store.displayAllOrders_between2dates(date1, date2);
	            break;
	            
			case 9:
				s.displayTop3Products() ;
				break;
				
	        case 10:display_customers_soreted();
	            break;
	            
	        case 11: 
	            	System.out.println("Enter the product ID you want: ");
					int cusRevPrd=input.nextInt();
                    display_all_customers_who_reviewed_prod(cusRevPrd);
                    break;

	        case 12: 
				all_Customers.displayAll();
				break;
			
			
			case 13: 
				all_Orders.displayAllOrders();
				break;

			
			case 14: 
				all_Products.displayAllProducts();
				break;
				
			case 15: 
				all_Reviews.displayAllReviews();
				break;			
				
			case 16:
				System.out.println("enter the order's ID: ");
				int oid=input.nextInt();
				input.nextLine();
				System.out.println("enter the customer's ID: ");
				int ocid=input.nextInt();
				input.nextLine();
				System.out.println("enter the Product IDs you want (put a ; between the ids): ");
				String prodids=input.nextLine();
				System.out.println("enter the Orders TOTAL PRICE: ");
				double tprice=input.nextDouble();
				input.nextLine();
				System.out.print("Enter date (yyyy-MM-dd): ");
				String dateInput = input.nextLine(); 
				LocalDate date = LocalDate.parse(dateInput);
				System.out.println("enter the order's STATUS: ");
				String status=input.nextLine();
				Order o = new Order(oid, ocid, prodids,
						tprice,date, status);
				add_Order(o);
				break;

            
			case 17: 
				System.out.println("enter the Review's ID: ");
				int rid=input.nextInt();
				input.nextLine();
				System.out.println("enter the Product ID you want make a review on: ");
				int prodid=input.nextInt();
				System.out.println("enter the RATING on the review(1-5): ");
				int rating=input.nextInt();
				System.out.println("enter the customer's ID: ");
				int rcid=input.nextInt();
				input.nextLine();
				System.out.println("enter the review's COMMENT: ");
				String comment=input.nextLine();
				
				Review r = new Review(rid, prodid,
						rating, rcid ,comment);
				add_Review(r);
				break;

            
			case 18:
				System.out.println("Enter customer ID you want all reviews for: ");
				int cIDrvs=input.nextInt();
				all_Customers.searchById(cIDrvs).displayReviews();
				break; 
				 
			
			case 19: 
				all_Products.displayOutOfStock();
				break;
				
				
			case 20:  
				System.out.println("Enter the product ID you want to display reviews for :");
				int previd=input.nextInt();
				Product prod= all_Products.Search_Product_by_id(previd);
				prod.displayReviews();
				break;
				
			
			
			
				
			case 21: 
				System.out.println("Enter Order ID you want to cancel:");
				int OrId= input.nextInt();
				all_Orders.searchOrderById(OrId).setStatus("Cancelled");
				break;
			
			case 22: 
				System.out.println("Enter Order id you want to update status for: ");
				int OSId=input.nextInt();
				input.nextLine();
				System.out.println("Enter updated status: ");
				String sts=input.nextLine();
				all_Orders.searchOrderById(OSId).setStatus(sts);
				break;
			
				
			case 23: 
				System.out.println("Enter the Order ID you want to search about :");
				int srchord=input.nextInt();
				all_Orders.searchOrderById(srchord).display();
				break;
			 
			case 24: 
				System.out.println("Enter Review id you want to update: ");
				int revId=input.nextInt();
				input.nextLine();
				System.out.println("Enter updated rating: ");
				int revRate=input.nextInt();
				input.nextLine();
				all_Reviews.Search_Review_by_id(revId).setRating(revRate);
				System.out.println("Enter updated comment: ");
				String Ucommnet=input.nextLine();
				all_Reviews.Search_Review_by_id(revId).setComment(Ucommnet);
				break;
			
			case 25: 
				System.out.println("Enter product ID to get an average rating for: ");
				int ARId= input.nextInt();
				System.out.println(all_Products.Search_Product_by_id(ARId).getAverageRating());
				break;
			
			default: System.out.println("Wrong Entry!");
            
			}
	
		}while(choice!=0);
		input.close();
		
		
	}}