import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class mainMethod {
    public static void main(String[] args) {
        ArrayList<Customer> customers = readFile();
        Scanner scanner = new Scanner(System.in);
        int input=-1;
        do {
            boolean isNumber;

            System.out.println("1. New Customer");
            System.out.println("2. Show all Customers");
            System.out.println("3. Find Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            do {
                isNumber=true;
                System.out.println("Enter number to continue (0 to stop):");
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch(NumberFormatException e){
                    isNumber = false;
                }
            }while(!isNumber && input>=0);
            switch (input) {
                case 1:
                    System.out.println("Enter First Name: ");
                    String firstname = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lastname = scanner.nextLine();
                    String email;
                    Iterator<Customer> iterator;
                    boolean found;
                    do {
                        found = false;
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
                    saveFile(customers);
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
                                int id = Integer.parseInt(scanner.nextLine());
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
                                    for (int i = 0; i <= customer.getFullName().length() - name.length(); i++) {
                                        if(customer.getFullName().toLowerCase().startsWith(name.toLowerCase(), i)) {
                                            mainfound = true;
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
                                        System.out.println(foundCustomers.get(i));
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
                    int id = -1;
                    do {
                        System.out.println("Enter customer ID to Update:");
                        try{
                            id = Integer.parseInt(scanner.nextLine());
                        } catch(NumberFormatException e){
                            isNumber = false;
                        }
                    }while(!isNumber && id>0);
                    System.out.println("-------------------------");
                    for (Customer customer : customers) {
                        if (customer.getId() == id) {
                            System.out.println("Enter new First Name (Enter to skip, current first name: " + customer.getFirstname() + ")");
                            firstname = scanner.nextLine();
                            if (firstname.equals("")||firstname == null) firstname = customer.getFirstname();

                            System.out.println("Enter new Last Name (Enter to skip, current last name: " + customer.getLastname() + ")");
                            lastname = scanner.nextLine();
                            if (lastname.equals("")|| lastname == null) lastname = customer.getLastname();

                            boolean found2;
                            do {
                                found2 = false;
                                System.out.println("Enter new e-mail-address (Enter to skip, current e-mail-address: " + customer.getEmail() + ")");
                                iterator = customers.iterator();
                                email = scanner.nextLine();
                                while (iterator.hasNext() && !email.equals("") && email != null) {
                                    Customer customer2 = iterator.next();
                                    if (customer2.getEmail().equals(email) && customer2.getId() != id) {
                                        found2 = true;
                                        System.out.println("This e-mail-address is not available, please enter another e-mail-address or press enter to skip");
                                    }
                                }
                            } while (found2);
                            if (email.equals("")||email == null) email = customer.getEmail();

                            if (customer.getPhone()== null)
                                System.out.println("Enter new phone number (Enter to skip, current phone number not set yet");
                            else
                                System.out.println("Enter new phone number (Enter to skip, current phone number: " + customer.getPhone() + ")");
                            phonenumber = scanner.nextLine();
                            if (phonenumber == null || phonenumber.equals("")) phonenumber = customer.getPhone();
                            if (phonenumber == null || phonenumber.equals("")) customer.setValues(firstname, lastname, email);
                            else customer.setValues(firstname, lastname, email, phonenumber);

                            found = true;
                        }
                    }

                    if(!found){
                        System.out.println("Customer Not Found");
                    }
                    else{
                        saveFile(customers);
                        System.out.println("Customer Info is Updated Successfully!");
                    }
                    System.out.println("-------------------------");
                    break;

                case 5:
                    found = false;
                    id=-1;
                    do {
                        System.out.println("Enter customer ID to Delete:");
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                        }catch(NumberFormatException e){
                            isNumber = false;
                        }
                    }while(!isNumber && id>0);
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
                        saveFile(customers);
                        System.out.println("Record is Deleted Successfully");
                    }
                    System.out.println("-------------------------");
                    break;
            }

        } while (input != 0);
    }

    private static ArrayList<Customer> readFile(){
        ArrayList<Customer> customers = new ArrayList<>();
        BufferedReader in = null;
        try{
            in = new BufferedReader(new FileReader("customers.txt"));
            String read;
            int count;
            boolean firstline = true;
            int maxId=0;
            while((read = in.readLine()) != null) {
                int id = 0;
                String firstname="", lastname="", email="", phone="";
                String[] splitted = read.split("\t");
                count = 0;
                for (String part : splitted) {
                    if(firstline){
                        maxId = Integer.parseInt(part);
                    }
                    else switch(count){
                        case 0:
                            id = Integer.parseInt(part);
                            break;
                        case 1:
                            firstname = part;
                            break;
                        case 2:
                            lastname = part;
                            break;
                        case 3:
                            email = part;
                            break;
                        case 4:
                            phone = part;
                            break;
                    }
                    count++;
                }
                if(firstline) firstline = false;
                else if(phone=="") customers.add(new Customer(id, firstname,lastname, email));
                else customers.add(new Customer(id, firstname,lastname, email, phone));
            }
            if(!customers.isEmpty()) customers.get(0).setMaxId(maxId);
            else new Customer("","","").setMaxId(0);
        }
        catch (FileNotFoundException e1){
            System.out.println("The file customers.txt didn't exist, a new file will be created");
        }
        catch (IOException e) {
            System.out.println("There was a problem reading the file");
        }
        finally{
            try{
                in.close();
            } catch (Exception e){
            }
        }
        return customers;
    }

    private static void saveFile(ArrayList<Customer> customers){
        try{
            FileWriter savedFile = new FileWriter("customers.txt");
            if(!customers.isEmpty()) savedFile.write(Integer.toString(customers.get(0).getMaxId())+"\n");
            for(Customer customer: customers){
                savedFile.write(customer.savingToString() + "\n");
            }
            savedFile.close();
        }
        catch (IOException e){
            System.out.println("An error occurred writing the file");
            e.printStackTrace();
        }
    }
}


