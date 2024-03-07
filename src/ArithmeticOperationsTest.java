
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class ArithmeticOperationsTest {

    @Test
    public void testAddition() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(+ 3 5)";
        String operation = "arithmetic";

        assertEquals("Resultado de la operación: 8.0\n", getOutput(arithmeticOperations, operation, input));
    }

    @Test
    public void testSubtraction() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(- 10 4)";
        String operation = "arithmetic";

        assertEquals("Resultado de la operación: 6.0\n", getOutput(arithmeticOperations, operation, input));
    }

    @Test
    public void testMultiplication() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(* 6 7)";
        String operation = "arithmetic";

        assertEquals("Resultado de la operación: 42.0\n", getOutput(arithmeticOperations, operation, input));
    }

    @Test
    public void testDivision() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(/ 20 4)";
        String operation = "arithmetic";

        assertEquals("Resultado de la operación: 5.0\n", getOutput(arithmeticOperations, operation, input));
    }

    @Test
    public void testInvalidExpression() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(+ 3)";
        String operation = "arithmetic";

        assertTrue(getOutput(arithmeticOperations, operation, input).contains("Error en la expresión aritmética"));
    }

    private String getOutput(ArithmeticOperations arithmeticOperations, String operation, String input) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        arithmeticOperations.processOperation(operation, input);

        System.setOut(System.out);
        return outContent.toString();
    }
}