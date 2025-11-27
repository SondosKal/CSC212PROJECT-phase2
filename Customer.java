package csc212project11;


public class Customer {
    private int customerId;
    private String name;
    private String email;
    private LinkedList<Order> orders;
    private LinkedList<Review> reviews;

    public Customer(int id, String name, String email) {
        this.customerId = id;
        this.name = name;
        this.email = email;
        this.orders = new LinkedList<>();
        reviews=new LinkedList<>();
    }

    public int getCustomerId() { 
        return customerId; 
    }

    public String getName() { 
        return name; 
    }

    public String getEmail() { 
        return email; 
    }

    public void addOrder(Order o) {
        orders.addLast(o);
    }
    
    public void addReview(Review r) {
    	reviews.addLast(r);
    }
    
    

    public void display() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("------------------------------------------------");
    }

    public void displayOrders() {
        if (orders.empty()) {
            System.out.println("No orders for customer " + name);
            return;
        }

        System.out.println("Orders for " + name + ":");
        orders.findFirst();
        while (true) {
            orders.retrieve().display();
            if (orders.last())
                break;
            orders.findNext();
        }
    }
    
    
    public void displayReviews() {
        if (reviews.empty()) {
            System.out.println("No reviews for customer " + name);
            return;
        }

        System.out.println("Reviews for " + name + ":");
        reviews.findFirst();
        while (true) {
            reviews.retrieve().display();
            if (reviews.last())
                break;
            reviews.findNext();
        }
    }
}