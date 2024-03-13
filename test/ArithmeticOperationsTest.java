import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArithmeticOperationsTest {

    Environment env = new Environment();
    
    @Test
    public void testSimpleAddition() {
        ArithmeticOperations operations = new ArithmeticOperations();
        assertEquals("Resultado de la operación: 3", operations.execute("(+ 1 2)", null));
    }

    @Test
    public void testNestedOperations() {
        ArithmeticOperations operations = new ArithmeticOperations();
        assertEquals("Resultado de la operación: 7", operations.execute("(+ 1 (* 2 3))", null));
    }

    @Test
    public void testDivisionByZero() {
        ArithmeticOperations operations = new ArithmeticOperations();
        assertEquals("Error: División por cero", operations.execute("(/ 1 0)", null));
    }

    @Test
    public void testInvalidExpression() {
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        String input = "(+ 3)";

        assertEquals("Error en la expresión aritmética", arithmeticOperations.execute(input, env));
    }
}
