import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EvaluatorTest {

    @Test
    public void testEvaluateArithmeticOperation() {
        Evaluator evaluator = new Evaluator();
        Environment env = new Environment();
        String expression = "(+ 2 3)";
        String expectedResult = "5"; // El resultado esperado de la operación 2 + 3
        String actualResult = evaluator.evaluate(expression, env);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEvaluateInvalidExpression() {
        Evaluator evaluator = new Evaluator();
        Environment env = new Environment();
        String expression = "invalid expression";
        String expectedResult = "Invalid expression";
        String actualResult = evaluator.evaluate(expression, env);
        assertEquals(expectedResult, actualResult);
    }

    
}
