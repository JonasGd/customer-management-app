

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class mainTest {

    public void emptyCustomers(){
        try {
            FileWriter emptyFile = new FileWriter("customers.txt");
            emptyFile.write("");
            emptyFile.close();
        }
        catch(IOException e){}
    }

    public void oneCustomer(){
        try {
            FileWriter emptyFile = new FileWriter("customers.txt");
            emptyFile.write("1\n1\ttest\ttest\ttest@test");
            emptyFile.close();
        }
        catch(IOException e){}
    }

    public void twoCustomers(){
        try {
            FileWriter emptyFile = new FileWriter("customers.txt");
            emptyFile.write("2\n1\ttest\ttest\ttest@test\n2\ttest2\ttest2\ttest2@test2\ttest");
            emptyFile.close();
        }
        catch(IOException e){}
    }


    @Test
    public void addTwoNewCustomers(){
        emptyCustomers();
        String userInput = "1\nJonas\nGedeshi\njonas.gedeshi@test.com\n0479000000\n1\nJonas\nGedeshi\njonas.gedeshi@test2.com\n0479000000\n0";
        String expected = "New customer created with customer id 2";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-7];

        Assert.assertEquals(expected, actual);
    }



    @Test
    public void addTwoNewCustomersSameMail(){
        emptyCustomers();
        String userInput = "1\nJonas\nGedeshi\njonas.gedeshi@test.com\n0479000000\n1\nJonas\nGedeshi\njonas.gedeshi@test.com\njonas.gedeshi@test2.com\n0479000000\n0";
        String expected = "New customer created with customer id 2";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-7];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addNewCustomer(){
        emptyCustomers();
        String userInput = "1\nJonas\nGedeshi\njonas.gedeshi@test.com\n0479000000\n0";
        String expected = "New customer created with customer id 1";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-7];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addNewCustomerWithoutPhone(){
        emptyCustomers();
        String userInput = "1\nJonas\nGedeshi\njonas.gedeshi@test.com\n\0\n0";
        String expected = "New customer created with customer id 1";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-7];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void showNoCustomers(){
        emptyCustomers();
        String userInput = "2\n0";
        String expected = "-------------------------";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void showOneCustomers(){
        oneCustomer();
        String userInput = "2\n0";
        String expected = "1 - test test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void showTwoCustomers(){
        twoCustomers();
        String userInput = "2\n0";
        String expected1 = "1 - test test test@test";
        String expected2 = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual1 = lines[lines.length-9];
        String actual2 = lines[lines.length-8];

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void findCustomersReturn(){
        twoCustomers();
        String userInput = "3\nx\n0";
        String expected = "x to return";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-7];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerById(){
        twoCustomers();
        String userInput = "3\na\n2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByNotExistingId(){
        oneCustomer();
        String userInput = "3\na\n2\n0";
        String expected = "Customer not found";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByFullName(){
        oneCustomer();
        String userInput = "3\nb\ntest test\n0";
        String expected = "1 - test test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByUniqueName(){
        twoCustomers();
        String userInput = "3\nb\ntest2 test2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByFirstName(){
        twoCustomers();
        String userInput = "3\nb\ntest2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByPartialName(){
        twoCustomers();
        String userInput = "3\nb\nest2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomersByNotUniqueName(){
        twoCustomers();
        String userInput = "3\nb\ntest\n0";
        String expected1 = "1 - test test test@test";
        String expected2 = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual1 = lines[lines.length-9];
        String actual2 = lines[lines.length-8];

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void findCustomersByMail(){
        twoCustomers();
        String userInput = "3\nc\ntest@test\n0";
        String expected = "1 - test test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomersByMailNotFound(){
        twoCustomers();
        String userInput = "3\nc\ntest@test2\n0";
        String expected = "Customer not found";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomersWrongCharacter(){
        twoCustomers();
        String userInput = "3\nd\nx\n0";
        String expected = "Please enter a valid character";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-11];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateFirstName(){
        oneCustomer();
        String empty = "";
        String userInput = "4\n1\ntest2\n"+empty+"\n"+empty+"\n"+empty+"\n3\na\n1\n0";
        String expected = "1 - test2 test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateAddPhonenumber(){
        oneCustomer();
        String empty = "";
        String userInput = "4\n1\n"+empty+"\n"+empty+"\n"+empty+"\n0479000000\n3\na\n1\n0";
        String expected = "1 - test test test@test 0479000000";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateMailToExisting(){
        twoCustomers();
        String empty = "";
        String userInput = "4\n1\n"+empty+"\n"+empty+"\ntest2@test2\ntest2@test\n"+empty+"\n3\na\n1\n0";
        String expected = "1 - test test test2@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateMailToSame(){
        twoCustomers();
        String empty = "";
        String userInput = "4\n1\n"+empty+"\n"+empty+"\ntest@test\n"+empty+"\n3\na\n1\n0";
        String expected = "1 - test test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void updateCustomerNotFound(){
        twoCustomers();
        String userInput = "4\n8\n0";
        String expected = "Customer Not Found";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteCustomer(){
        oneCustomer();
        String userInput = "5\n1\n2\n0";
        String expected = "-------------------------";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }


    public String[]  runWithInput(String userInput){

                ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
                System.setIn(bais);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        mainMethod.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        return lines;

    }

}
