import java.util.ArrayList;

public class Customer{
    private static int maxId;
    private int id;
    private String firstname, lastname,email,phone;

    public Customer(ArrayList<Customer> customers, String firstname, String lastname, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        id = ++maxId;
    }

    public Customer(ArrayList<Customer> customers,String firstname, String lastname, String email, String phone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        id = ++maxId;
    }

    public int getId(){
        return id;
    }

    public String getFullName() { return (firstname + " " +lastname); }

}
