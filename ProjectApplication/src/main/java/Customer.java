import java.util.ArrayList;
import java.util.Collections;

public class Customer implements Comparable<Customer>{
    private int id;
    private String firstname, lastname,email,phone;

    public Customer(ArrayList<Customer> customers, String firstname, String lastname, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        id = Collections.max(customers).getId() + 1;
    }

    public Customer(ArrayList<Customer> customers,String firstname, String lastname, String email, String phone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        id = Collections.max(customers).getId() + 1;
    }

    public int getId(){
        return id;
    }

    @Override
    public int compareTo(Customer o) {
        if (this.getId() > o.getId()){
            return 1;
        }
        else if (this.getId() < o.getId()){
            return -1;
        }
        return 0;
    }
}
