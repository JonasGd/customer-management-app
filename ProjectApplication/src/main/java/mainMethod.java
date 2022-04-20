import java.util.ArrayList;
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
            System.out.println("Enter number to continue (0 to stop):");
            input = scanner.nextInt();

            switch(input){
                case 1:
                    System.out.println("Enter First Name: ");
                    String firstname = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lastname = scanner.nextLine();
                    System.out.println("Enter e-mail-address: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter phone number (optional): ");
                    String phonenumber = scanner.nextLine();
                    if(phonenumber.equals("")) customers.add(new Customer(customers, firstname, lastname, email));
                    else customers.add(new Customer(customers, firstname, lastname, email, phonenumber));
                    System.out.println("New customer created with customer id "+ customers.get(customers.size()).getId());
                break;

                case 2:



                    break;
                case 3:
                    System.out.println("a. Find by id");
                    System.out.println("b. Find by name");
                    System.out.println("c. Find by e-mail-address");
                    String input2 = scanner.nextLine();
                    switch(input2){
                        case "a":

                            break;
                        case "b":

                            break;

                        case "c":

                            break;
                        default:
                    }

                break;

                case 4:
                    break;

                case 5:
                    break;
            }

        }while(input!=0);
    }
}
