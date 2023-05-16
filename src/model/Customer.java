package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;
    private String emailRegex = "^(.+)@(.+).(.+)$";


    public Customer(String firstName,String lastName,String email){

        Pattern pattern = Pattern.compile(emailRegex);

        if(pattern.matcher(email).matches())
            this.email=email;
        else  throw new IllegalArgumentException("The email dose not match the format");

        this.firstName=firstName;
        this.lastName=lastName;


    }


    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return"the customer name is: "+firstName+" "+lastName+", "+"the email address is: "+email;
    }
}
