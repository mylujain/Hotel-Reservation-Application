package service;

import model.Customer;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class CustomerService {
    private static final CustomerService customerservice = new CustomerService();
    private final Map<String, Customer> mapOfCustomer = new HashMap<String, Customer>();


    public void addCustomer(String email, String firstName,String lastName){
        mapOfCustomer.put(email, new Customer(firstName, lastName, email));

    }

    public static CustomerService getcustomerservice() {
        return customerservice;
    }

    public Customer getCustomer(String customerEmail){
      return mapOfCustomer.get(customerEmail);
    }

    public Collection<Customer> getAllCustomer(){
        return mapOfCustomer.values();
    }
}
