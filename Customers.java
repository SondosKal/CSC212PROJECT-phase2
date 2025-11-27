package csc212project11;

import java.io.File;
import java.util.Scanner;

public class Customers {
    private AVL<Customer> customers;

    public Customers() {
        customers = new AVL<>();
    }

    Customers(AVL<Customer> input_customers) {
        customers = input_customers;
    }

    public AVL<Customer> get_customers() {
        return customers;
    }

    public Customer searchById(int id) {
        if (customers.empty()) 
            return null;
        boolean found=customers.findKey(id);
        if(found)
            return customers.retrieve();
        else
            return null;
    }

    public void addCustomer(Customer c) {
        if (searchById(c.getCustomerId()) == null) {
            customers.insert(c.getCustomerId(),c);
            //System.out.println(" Added customer: " + c.getName());
        } else {
            System.out.println(" Customer with ID " + c.getCustomerId() + " already exists!");
        }
    }

    public void displayAll() {
        System.out.println("=== All customers ===");
       if ( customers.empty()){
           System.out.println("no customers exist");
         return ;
       }
     else{
           inOrder_all_customers( customers.getRoot());
         }
   }
    
    private void inOrder_all_customers(BSTNode<Customer>c)
	{
	if(c==null) return;
	inOrder_all_customers(c.left);   
              
                   c.data.display();                   
                    System.out.println("***************************");
                    System.out.println("***************************");
           
	inOrder_all_customers(c.right);
	}

    public void loadCustomers(String fileName) {
        try {
            File f = new File(fileName);
            Scanner read = new Scanner(f);
            System.out.println(" Reading file: " + fileName);
            System.out.println();

            if (read.hasNextLine())
                read.nextLine();

            while (read.hasNextLine()) {
                String line = read.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] a = line.split(",");
                int id = Integer.parseInt(a[0].trim());
                String name = a[1].trim();
                String email = a[2].trim();

                Customer c = new Customer(id, name, email);
                addCustomer(c);
            }

            read.close();
            System.out.println("Customers loaded successfully!\n");
        } catch (Exception e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }
}