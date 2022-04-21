import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class mainMethod {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.println("1. New Customer");
            System.out.println("2. Show all Customers");
            System.out.println("3. Find Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("Enter number to continue (0 to stop, 9 to save):");
            input = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (input) {
                case 1:
                    System.out.println("Enter First Name: ");
                    String firstname = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lastname = scanner.nextLine();
                    String email;
                    Iterator<Customer> iterator;
                    boolean found = false;
                    do {
                        System.out.println("Enter e-mail-address: ");
                        iterator = customers.iterator();
                        email = scanner.nextLine();
                        while (iterator.hasNext()) {
                            Customer customer = iterator.next();
                            if (customer.getEmail().equals(email)) {
                                found = true;
                                System.out.println("This e-mail-address already exists in the database, please enter another e-mail-address");
                            }
                        }
                    } while (found);
                    System.out.println("Enter phone number (optional): ");
                    String phonenumber = scanner.nextLine();
                    if (phonenumber.equals("")) customers.add(new Customer(firstname, lastname, email));
                    else customers.add(new Customer(firstname, lastname, email, phonenumber));
                    System.out.println("New customer created with customer id " + customers.get(customers.size() - 1).getId());
                    break;

                case 2:
                    System.out.println("-------------------------");
                    iterator = customers.iterator();
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    System.out.println("-------------------------");

                    break;
                case 3:
                    boolean validChar;
                    do {
                        System.out.println("a. Find by id");
                        System.out.println("b. Find by name");
                        System.out.println("c. Find by e-mail-address");
                        System.out.println("x to return");
                        String input2 = scanner.nextLine();
                        validChar = true;
                        switch (input2) {
                            case "a":
                                found = false;
                                System.out.println("Enter id to Search:");
                                int id = scanner.nextInt();
                                System.out.println("-------------------------");
                                iterator = customers.iterator();
                                while (iterator.hasNext()) {
                                    Customer customer = iterator.next();
                                    if (customer.getId() == id) {
                                        System.out.println(customer);
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    System.out.println("Customer not found");
                                }
                                System.out.println("-------------------------");
                                break;

                            case "b":
                                ArrayList<Customer> foundCustomers = new ArrayList<>();
                                System.out.println("Enter first or last name (or part of names) to Search:");
                                String name = scanner.nextLine();
                                System.out.println("-------------------------");
                                iterator = customers.iterator();
                                while (iterator.hasNext()) {
                                    Customer customer = iterator.next();
                                    boolean mainfound = false;
                                    boolean localnotfound;
                                    for (int i = 0; i <= customer.getFullName().length() - name.length(); i++) {
                                        localnotfound = true;
                                        for (int j = 0; j < name.length(); j++) {
                                            if (!customer.getFullName().substring(i + j, i + j + 1).equals(name.substring(j, j + 1))) {
                                                localnotfound = false;
                                            }
                                            if(localnotfound) mainfound = true;
                                        }
                                    }
                                    if (mainfound) {
                                        foundCustomers.add(customer);
                                    }
                                }
                                if (foundCustomers.size() == 0) {
                                    System.out.println("Customer not found");
                                } else if (foundCustomers.size() == 1) {
                                    System.out.println(foundCustomers.get(0));
                                } else {
                                    for (int i = 0; i < foundCustomers.size(); i++) {
                                        System.out.println((i + 1) + ". " + foundCustomers.get(i));
                                    }
                                }
                                System.out.println("-------------------------");
                                break;

                            case "c":
                                found = false;
                                System.out.println("Enter e-mail-address to Search:");
                                String mail = scanner.nextLine();
                                System.out.println("-------------------------");
                                iterator = customers.iterator();
                                while (iterator.hasNext()) {
                                    Customer customer = iterator.next();
                                    if (customer.getEmail().equals(mail)) {
                                        System.out.println(customer);
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    System.out.println("Customer not found");
                                }
                                System.out.println("-------------------------");
                                break;
                            case "x":
                                break;
                            default:
                                validChar = false;
                                System.out.println("Please enter a valid character");
                        }
                    } while (!validChar);

                    break;

                case 4:
                    found = false;
                    System.out.println("Enter customer ID to Update:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.println("-------------------------");
                    for (Customer customer : customers) {
                        if (customer.getId() == id) {
                            System.out.println("Enter new First Name (Enter to skip, current first name: " + customer.getFirstname() + ")");
                            firstname = scanner.nextLine();
                            if (firstname.equals("")) firstname = customer.getFirstname();

                            System.out.println("Enter new Last Name (Enter to skip, current last name: " + customer.getLastname() + ")");
                            lastname = scanner.nextLine();
                            if (lastname.equals("")) lastname = customer.getLastname();

                            boolean found2 = false;
                            do {
                                System.out.println("Enter new e-mail-address (Enter to skip, current e-mail-address: " + customer.getEmail() + ")");
                                iterator = customers.iterator();
                                email = scanner.nextLine();
                                while (iterator.hasNext() && !email.equals("")) {
                                    Customer customer2 = iterator.next();
                                    if (customer2.getEmail().equals(email) && customer2.getId() != id) {
                                        found2 = true;
                                        System.out.println("This e-mail-address is not available, please enter another e-mail-address or press enter to skip");
                                    }
                                }
                            } while (found2);
                            if (email.equals("")) email = customer.getEmail();

                            if (customer.getPhone()== null)
                                System.out.println("Enter new phone number (Enter to skip, current phone number not set yet");
                            else
                                System.out.println("Enter new phone number (Enter to skip, current phone number: " + customer.getPhone() + ")");
                            phonenumber = scanner.nextLine();
                            if (phonenumber.equals("")) phonenumber = customer.getPhone();
                            if (phonenumber.equals("")) customer.setValues(firstname, lastname, email);
                            else customer.setValues(firstname, lastname, email, phonenumber);

                            found = true;
                        }
                    }

                    if(!found){
                        System.out.println("Customer Not Found");
                    }
                    else{
                        System.out.println("Customer Info is Updated Successfully!");
                    }
                    System.out.println("-------------------------");
                    break;

                case 5:
                    found = false;
                    System.out.println("Enter customer ID to Delete:");
                    id = scanner.nextInt();
                    System.out.println("-------------------------");
                    iterator = customers.iterator();
                    while (iterator.hasNext()) {
                        Customer customer = iterator.next();
                        if (customer.getId() == id) {
                            iterator.remove();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Record Not Found");
                    } else {
                        System.out.println("Record is Deleted Successfully");
                    }
                    System.out.println("-------------------------");
                    break;
            }

        } while (input != 0);
    }

    private void readFile(){

    }

    private void saveFile(){

    }
}


