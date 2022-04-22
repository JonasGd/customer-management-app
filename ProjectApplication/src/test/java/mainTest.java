import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class mainTest {
    @Before
    public void emptyCustomers(){
        try {
            FileWriter emptyFile = new FileWriter("customers.txt");
            emptyFile.close();
        }
        catch(IOException e){}
    }

    @Test
    public void addNewCustomer(){
        String userInput = String.format("1%sJonas%sGedeshi%sjonas.gedeshi@test.com%s",
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "New customer created with customer id 1";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        mainMethod.main(null);

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        Assert.assertEquals(expected, actual);
    }

}
