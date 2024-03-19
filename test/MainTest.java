import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void testMain() {
        // Prepare input for the scanner
        String inputString = "( + 2 3 )\n( - 5 2 )\n( exit )\n";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);

        // Redirect output to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Run the main method
        Main.main(new String[]{});

        // Capture and split the output into lines
        String[] lines = outputStream.toString().split(System.lineSeparator());

        // Assert the results
        assertEquals("Welcome to the LISP Compiler", lines[0]);
        assertEquals("5", lines[1]);
        assertEquals("3", lines[2]);
    }
}
