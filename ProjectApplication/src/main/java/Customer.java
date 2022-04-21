import java.util.ArrayList;

public class Customer{
    private static int maxId;
    private int id;
    private String firstname, lastname,email,phone;

    public Customer(String firstname, String lastname, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        id = ++maxId;
    }

    public Customer(String firstname, String lastname, String email, String phone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        id = ++maxId;
    }

    public void setValues(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public void setValues(String firstname, String lastname, String email, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstname(){ return firstname;}
    public String getLastname(){ return lastname;}
    public String getPhone(){ return phone;}

    public String getEmail() { return email; }
    public int getId(){
        return id;
    }

    public String getFullName() { return (firstname + " " +lastname); }

    @Override
    public String toString() {
        if(phone == null || phone.equals("")) return id + " - " + firstname + " " + lastname + " " + email;
        else return id + " - " + firstname + " " + lastname + " " + email + " " + phone;
    }
}
