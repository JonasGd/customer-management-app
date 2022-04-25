

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

    public void OneCustomer(){
        try {
            FileWriter emptyFile = new FileWriter("customers.txt");
            emptyFile.write("1\n1\ttest\ttest\ttest@test");
            emptyFile.close();
        }
        catch(IOException e){}
    }

    public void TwoCustomers(){
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
        OneCustomer();
        String userInput = "2\n0";
        String expected = "1 - test test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void showTwoCustomers(){
        TwoCustomers();
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
        TwoCustomers();
        String userInput = "3\nx\n0";
        String expected = "x to return";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-7];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerById(){
        TwoCustomers();
        String userInput = "3\na\n2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByNotExistingId(){
        OneCustomer();
        String userInput = "3\na\n2\n0";
        String expected = "Customer not found";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByFullName(){
        OneCustomer();
        String userInput = "3\nb\ntest test\n0";
        String expected = "1 - test test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByUniqueName(){
        TwoCustomers();
        String userInput = "3\nb\ntest2 test2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByFirstName(){
        TwoCustomers();
        String userInput = "3\nb\ntest2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomerByPartialName(){
        TwoCustomers();
        String userInput = "3\nb\nest2\n0";
        String expected = "2 - test2 test2 test2@test2 test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomersByNotUniqueName(){
        TwoCustomers();
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
        TwoCustomers();
        String userInput = "3\nc\ntest@test\n0";
        String expected = "1 - test test test@test";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomersByMailNotFound(){
        TwoCustomers();
        String userInput = "3\nc\ntest@test2\n0";
        String expected = "Customer not found";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-8];

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findCustomersWrongCharacter(){
        TwoCustomers();
        String userInput = "3\nd\nx\n0";
        String expected = "Please enter a valid character";

        String[] lines = runWithInput(userInput);
        String actual = lines[lines.length-11];

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
