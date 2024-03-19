import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefunTest {

    @Test
    void testExecute() {
        // Mock environment for testing
        Environment env = new Environment();

        // Define a function using Defun
        String input = "( defun suma { a b } { + a b } )";
        Defun defun = new Defun();
        String result = defun.execute(input, env);

        // Test the result
        assertEquals("suma {set}", result);

        // Now, call the defined function
        String callInput = "( suma 2 3 )";
        String callResult = defun.executeFunction(callInput, env);

        // Assert the result of the function call
        assertEquals("5", callResult);
    }
}
