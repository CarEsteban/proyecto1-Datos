import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArithmeticOperationsTest {

    @Test
    public void testAddition() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(+ 3 5)";

        assertEquals("Resultado de la operación: 8.0", arithmeticOperations.processOperation(input));
    }

    @Test
    public void testSubtraction() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(- 10 4)";

        assertEquals("Resultado de la operación: 6.0", arithmeticOperations.processOperation(input));
    }

    @Test
    public void testMultiplication() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(* 6 7)";

        assertEquals("Resultado de la operación: 42.0", arithmeticOperations.processOperation(input));
    }

    @Test
    public void testDivision() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(/ 20 4)";

        assertEquals("Resultado de la operación: 5.0", arithmeticOperations.processOperation(input));
    }

    @Test
    public void testInvalidExpression() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(+ 3)";

        assertEquals("Error en la expresión aritmética", arithmeticOperations.processOperation(input));
    }
}
