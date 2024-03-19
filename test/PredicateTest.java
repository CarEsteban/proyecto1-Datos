import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PredicateTest {

    private Predicate predicate;
    private Environment environment;

    @Before
    public void setUp() {
        predicate = new Predicate();
        environment = new Environment();
        // Configurar el entorno con algunas variables de prueba
        environment.defineVariable("num1", "10");
        environment.defineVariable("num2", "20");
        environment.defineVariable("str1", "test");
        environment.defineVariable("list1", "(a b c)");
    }

    @Test
    public void testAtomWithLiteral() {
        assertEquals("true", predicate.execute("(atom a)", environment));
        assertEquals("true", predicate.execute("(atom 123)", environment));

    }

    @Test
    public void testAtomWithVariable() {
        assertEquals("true", predicate.execute("(atom num1)", environment));
        assertEquals("true", predicate.execute("(atom str1)", environment));
    }

    @Test
    public void testAtomWithListVariable() {
        assertEquals("nil", predicate.execute("(atom list1)", environment));
    }

    @Test
    public void testListWithVariable() {
        assertEquals("true", predicate.execute("(list list1)", environment));
        assertEquals("false", predicate.execute("(list str1)", environment));
    }

    @Test
    public void testEqualWithVariables() {
        environment.defineVariable("num3", "10");
        assertEquals("true", predicate.execute("(equal num1 num3)", environment));
        assertEquals("false", predicate.execute("(equal num1 num2)", environment));
    }

    @Test
    public void testLessThanWithVariables() {
        assertEquals("true", predicate.execute("(< num1 num2)", environment));
        assertEquals("false", predicate.execute("(< num2 num1)", environment));
    }

    @Test
    public void testGreaterThanWithVariables() {
        assertEquals("false", predicate.execute("(> num1 num2)", environment));
        assertEquals("true", predicate.execute("(> num2 num1)", environment));
    }

    @Test
    public void testInvalidArguments() {
        assertEquals("Error: entrada no válida.", predicate.execute("(atom)", environment));
        assertEquals("Error: Uno de los operandos no es un número válido",
                predicate.execute("(< str1 num2)", environment));
    }
}
